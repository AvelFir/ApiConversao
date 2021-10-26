package br.com.guilherme.apiConversao.conversor;


import br.com.guilherme.apiConversao.external.FileClient;
import br.com.guilherme.apiConversao.external.UploadResource;
import br.com.guilherme.apiConversao.services.UriService;
import feign.Feign;
import feign.Response;
import feign.form.spring.SpringFormEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@RestController
@RequestMapping("/apiconversor/arquivo")
public class conversorArquivos {

    WebClient webClient = WebClient.create();

    @Autowired
    FileClient fileClient;

    @PostMapping(consumes = "multipart/form-data")
    public String uploadFileWithManualClient(@RequestParam String table_name,
                                             @RequestParam String table_sys_id,
                                             @RequestParam MultipartFile uploadFile,
                                             @RequestHeader(value = "Authorization",required = false) String authorization,
                                             HttpServletRequest request) {
        URI uri = UriService.geraUriCorretamente(request.getQueryString());
        UploadResource fileUploadResource = Feign.builder().encoder(new SpringFormEncoder())
                .target(UploadResource.class, uri.toString());
        Response response = fileUploadResource.uploadFile(table_name,table_sys_id,uploadFile);
        return response.toString();
    }

    //Multipart - 1
//    @PostMapping
//    public String uploadFileWithManualClient(@RequestParam MultipartFile file,
//                                              @RequestHeader(value = "Authorization",required = false) String authorization,
//                                              HttpServletRequest request) {
//        URI uri = UriService.geraUriCorretamente(request.getQueryString());
//        UploadResource fileUploadResource = Feign.builder().encoder(new SpringFormEncoder())
//                .target(UploadResource.class, uri.toString());
//        Response response = fileUploadResource.uploadFile(file);
//        return response.toString();
//    }


//    public ResponseEntity<Object> uploadMultipart(@RequestParam MultipartFile file,
//                                                  @RequestHeader(value = "Authorization",required = false) String authorization,
//                                                  HttpServletRequest request){
//        URI uri = UriService.geraUriCorretamente(request.getQueryString());
//        return fileClient.fileUpload(uri,authorization,file);
//    }

//    @PostMapping
//    public ResponseEntity<Object> uploadAsBinary(HttpServletRequest request) throws IOException {
//        URI uri = UriService.geraUriCorretamente(request.getQueryString());
//        MediaType contentType = MediaType.valueOf(request.getContentType());
//        InputStream file = request.getInputStream();
//        ResponseEntity<Object> result = webClient.post()
//                .uri(uri).accept(MediaType.APPLICATION_JSON)
//                .contentType(contentType)
//                .body(BodyInserters.fromValue(file.readAllBytes().toString()))
//                .retrieve()
//                .toEntity(Object.class).block();
//        return result;
//    }



//    @PostMapping
//    public ResponseEntity<Object> uploadAsBinary(HttpServletRequest request) throws IOException {
//        URI uri = UriService.geraUriCorretamente(request.getQueryString());
//        MediaType contentType = MediaType.valueOf(request.getContentType());
//        InputStream file = request.getInputStream();
////        System.out.println("URI: " + uri.toString());
//        System.out.println("Type: " + contentType.getSubtype());
//        System.out.println("File: " + file.readAllBytes().toString());
//        ResponseEntity<Object> result = webClient.post()
//                .uri(uri).accept(MediaType.APPLICATION_JSON)
//                .contentType(contentType)
//                .body(BodyInserters.fromValue(file.readAllBytes().toString()))
//                .retrieve()
//                .toEntity(Object.class).block();
//        return result;
//    }
//
//    @PostMapping
//    public String upload(HttpServletRequest request) throws IOException {
//        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println(request.getHeader(HttpHeaders.CONTENT_TYPE));
//        URI uri = UriService.geraUriCorretamente(request.getQueryString());
//        byte[] file = request.getInputStream().readAllBytes();
//        ResponseEntity<Object> response = apiClient.postFile(uri,authorization,file);
//        return "Upload com sucesso";
//    }



}
