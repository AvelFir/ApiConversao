package br.com.guilherme.apiConversao.conversor;


import br.com.guilherme.apiConversao.external.UploadResourceClient;
import br.com.guilherme.apiConversao.services.UriService;
import feign.Feign;
import feign.Response;
import feign.form.spring.SpringFormEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/apiconversor/arquivo")
public class ArquivosController {

    private final Logger logger = LoggerFactory.getLogger("Conversor Arquivos");

    @PostMapping(consumes = "multipart/form-data")
    public String uploadFileWithManualClient(@RequestParam String table_name,
                                             @RequestParam String table_sys_id,
                                             @RequestParam MultipartFile uploadFile,
                                             @RequestHeader(value = "Authorization",required = false) String authorization,
                                             HttpServletRequest request) {
        log(HttpMethod.POST,authorization,request);
        URI uri = UriService.geraUriCorretamente(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8));
        logger.info("Uri Enviada pro feign: {}",uri);
        UploadResourceClient fileUploadResourceClient = Feign.builder().encoder(new SpringFormEncoder())
                .target(UploadResourceClient.class, uri.toString());
        logger.info("Enviando Requisição");
        Response response = fileUploadResourceClient.uploadFile(authorization,table_name,table_sys_id,uploadFile);
        logger.info("Resposta Recebida Status={}",response.status());
        return response.toString();
    }

    public void log(HttpMethod httpMethod, String authorization, HttpServletRequest request){
        logger.info("Inicio {}",httpMethod);
        logger.info("Possui authorization: {}", authorization != null ? "Sim" : "Nao");
        logger.info("URI recebida: {}", request.getQueryString());
    }
}
