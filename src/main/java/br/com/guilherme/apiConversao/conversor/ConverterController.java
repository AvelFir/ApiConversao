package br.com.guilherme.apiConversao.conversor;

import br.com.guilherme.apiConversao.external.ApiClient;
import br.com.guilherme.apiConversao.services.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequestMapping("/apiconversor")
@RestController
public class ConverterController {

    @Autowired
    ApiClient apiClient;

    private final Logger logger = LoggerFactory.getLogger("Conversor");

    @PostMapping
    public ResponseEntity<Object> postConversor(@RequestBody(required = false) Object body,
                                @RequestHeader(value = "Authorization",required = false) String authorization,
                                HttpServletRequest request){
        log(HttpMethod.POST,authorization,request);
        URI uri = UriService.geraUriCorretamente(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8));
        logger.info("Uri Enviada pro feign: {}",uri);
        Optional<Object> optional = Optional.ofNullable(body);
        logger.info("Enviando Requisição");
        ResponseEntity<Object> response = apiClient.postConversor(uri,authorization,optional.orElse(""));
        logger.info("Resposta Recebida Status={}",response.getStatusCode());
        return response;
    }
    @GetMapping
    public ResponseEntity<Object> getConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                HttpServletRequest request){
        log(HttpMethod.POST,authorization,request);
        URI uri = UriService.geraUriCorretamente(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8));
        ResponseEntity<Object> response = apiClient.getConversor(uri, authorization);
        logger.info("Resposta Recebida Status={}",response.getStatusCode());
        return response;
    }
    @PutMapping
    public ResponseEntity<Object> putConversor(@RequestBody(required = false) Object body,
                                                @RequestHeader(value = "Authorization",required = false) String authorization,
                                               HttpServletRequest request, UriComponentsBuilder builder){
        log(HttpMethod.POST,authorization,request);
        URI uri = UriService.geraUriCorretamente(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8));
        Optional<Object> optional = Optional.ofNullable(body);
        ResponseEntity<Object> response = apiClient.putConversor(uri, authorization, optional.orElse(""));
        logger.info("Resposta Recebida Status={}",response.getStatusCode());
        return response;
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                   HttpServletRequest request, UriComponentsBuilder builder){
        log(HttpMethod.POST,authorization,request);
        URI uri = UriService.geraUriCorretamente(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8));
        ResponseEntity<Object> response = apiClient.deleteConversor(uri, authorization);
        logger.info("Resposta Recebida Status={}",response.getStatusCode());
        return response;
    }

    public void log(HttpMethod httpMethod, String authorization, HttpServletRequest request){
        logger.info("Inicio {}",httpMethod);
        logger.info("Possui authorization: {}", authorization != null ? "Sim" : "Nao");
        logger.info("URI recebida: {}", request.getQueryString());
    }
}
