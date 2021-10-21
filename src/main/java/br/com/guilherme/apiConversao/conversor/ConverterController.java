package br.com.guilherme.apiConversao.conversor;

import br.com.guilherme.apiConversao.exceptionHandler.UrlPadraoException;
import br.com.guilherme.apiConversao.external.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/apiconversor")
@RestController
public class ConverterController {

    @Autowired
    ApiClient apiClient;

    @PostMapping
    public ResponseEntity<Object> postConversor(@RequestBody(required = false) Object body,
                                                           @RequestHeader(value = "Authorization",required = false) String authorization,
                                                           @RequestParam Map<String, String> url){
        String urlValidada = validaUrl(url.toString());
        URI uri = URI.create(urlValidada);
        Optional<Object> optional = Optional.ofNullable(body);
        return apiClient.postConversor(uri,authorization,optional.orElse(""));
    }
    @GetMapping
    public ResponseEntity<Object> getConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                @RequestParam Map<String, Object> url){
        String urlValidada = validaUrl(url.toString());
        URI uri = URI.create(urlValidada);
        return apiClient.getConversor(uri,authorization);
    }
    @PutMapping
    public ResponseEntity<Object> putConversor(@RequestBody(required = false) Object body,
                                                @RequestHeader(value = "Authorization",required = false) String authorization,
                                                @RequestParam Map<String, Object> url){
        String urlValidada = validaUrl(url.toString());
        URI uri = URI.create(urlValidada);
        Optional<Object> optional = Optional.ofNullable(body);
        return apiClient.putConversor(uri,authorization,optional.orElse(""));
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteConversor( @RequestHeader(value = "Authorization",required = false) String authorization,
                                                @RequestParam Map<String, Object> url){
        String urlValidada = validaUrl(url.toString());
        URI uri = URI.create(urlValidada);
        return apiClient.deleteConversor(uri,authorization);
    }


    private String validaUrl(String url){
        if(!url.startsWith("{url=")){
            throw new UrlPadraoException("O parametro url= nao foi passado Ou esta incorreto");
        }
        return limpa(url);
    }

    private String limpa(String url) {
        return url.replace("{","").replace("}","").replace("url=","").replace(" ", "").replace(",","&");
    }
}
