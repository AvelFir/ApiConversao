package br.com.guilherme.apiConversao.conversor;

import br.com.guilherme.apiConversao.external.ApiClient;
import br.com.guilherme.apiConversao.services.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RequestMapping("/apiconversor")
@RestController
public class ConverterController {

    @Autowired
    ApiClient apiClient;

    @PostMapping
    public ResponseEntity<Object> postConversor(@RequestBody(required = false) Object body,
                                @RequestHeader(value = "Authorization",required = false) String authorization,
                                HttpServletRequest request){
        System.out.println(authorization);
        URI uri = UriService.geraUriCorretamente(request.getQueryString());
        Optional<Object> optional = Optional.ofNullable(body);
        return apiClient.postConversor(uri,authorization,optional.orElse(""));
    }
    @GetMapping
    public ResponseEntity<Object> getConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                HttpServletRequest request, UriComponentsBuilder builder){
        URI uri = UriService.geraUriCorretamente(request.getQueryString());
        return apiClient.getConversor(uri,authorization);
    }
    @PutMapping
    public ResponseEntity<Object> putConversor(@RequestBody(required = false) Object body,
                                                @RequestHeader(value = "Authorization",required = false) String authorization,
                                               HttpServletRequest request, UriComponentsBuilder builder){
        URI uri = UriService.geraUriCorretamente(request.getQueryString());
        Optional<Object> optional = Optional.ofNullable(body);
        return apiClient.putConversor(uri,authorization,optional.orElse(""));
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                   HttpServletRequest request, UriComponentsBuilder builder){
        URI uri = UriService.geraUriCorretamente(request.getQueryString());
        return apiClient.deleteConversor(uri,authorization);
    }
}
