package br.com.guilherme.apiConversao.external;


import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeignConfig {

    private final Logger logger = LoggerFactory.getLogger("Feign Request Interceptor");

    @Bean
    public RequestInterceptor requestInterceptor(){
        return template -> {
           var queries = template.queries().entrySet().stream().collect(Collectors.toMap(
                   Map.Entry::getKey,
                   entry -> {
                       if(entry.getValue().size() > 1){
                           logger.info("QueryParam possui , : {}",entry.getValue());
                           return List.of(String.join(",", entry.getValue()));
                       }
                       return entry.getValue();
                   }
           ));
           template.queries(null);
           template.queries(queries);
           logger.info("Headers: {}", template.headers());
           logger.info("URL Saida: {}", template.url());
        };
    }

    public class FeignSupportConfig {
        @Bean
        public Encoder multipartFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
                @Override
                public HttpMessageConverters getObject() throws BeansException {
                    return new HttpMessageConverters(new RestTemplate().getMessageConverters());
                }
            }));
        }
    }
}