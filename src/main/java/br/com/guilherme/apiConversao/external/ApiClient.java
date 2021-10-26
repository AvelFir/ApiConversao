package br.com.guilherme.apiConversao.external;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URI;

@FeignClient(value = "api", url = "apiclient",configuration = {FeignConfig.class})
public interface ApiClient {

    @PostMapping
    public ResponseEntity<Object> postConversor(URI uri,
                                                @RequestHeader(value = "Authorization")String  authorization,
                                                @RequestBody(required = false) Object body);
    @PutMapping
    public ResponseEntity<Object> putConversor(URI uri,
                                                @RequestHeader(value = "Authorization")String  authorization,
                                                @RequestBody(required = false) Object body);
    @GetMapping
    public ResponseEntity<Object> getConversor(URI uri, @RequestHeader(value = "Authorization")String authorization);

    @DeleteMapping
    public ResponseEntity<Object> deleteConversor(URI uri, @RequestHeader(value = "Authorization")String authorization);

//    @PostMapping
//    @Headers("Content-Type: image/jpeg")
//    public ResponseEntity<Object> postFile(URI uri,
//                                            @RequestHeader(value = "Authorization")String  authorization,
//                                           byte[] blob);
}