package br.com.guilherme.apiConversao.external;

import br.com.guilherme.apiConversao.conversor.FormDataDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(value = "apifile", url = "apiclient",configuration = {FeignConfig.class})
public interface FileClient {

    @Headers("Content-Type: multipart/form-data")
    @PostMapping
    ResponseEntity<Object> fileUpload(URI uri,
                                      @RequestHeader(value = "Authorization")String  authorization,
                                      @RequestBody FormDataDTO object);
}
