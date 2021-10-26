package br.com.guilherme.apiConversao.external;


import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
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

    @Bean
    public RequestInterceptor requestInterceptor(){
        return template -> {
           var queries = template.queries().entrySet().stream().collect(Collectors.toMap(
                   Map.Entry::getKey,
                   entry -> {
                       if(entry.getValue().size() > 1){
                           System.out.println("Entrou no if: " + entry.getValue().toString());
                           return List.of(String.join(",", entry.getValue()));
                       }
                       return entry.getValue();
                   }
           ));

           template.queries(null);
           template.queries(queries);
            System.out.println(template.headers().toString());
            System.out.println("URl saida: " + template.url().toString());
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