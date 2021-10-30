package br.com.guilherme.apiConversao.teste;

import br.com.guilherme.apiConversao.external.ApiClient;
import br.com.guilherme.apiConversao.services.UriService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

//@RequestMapping("/teste")
//@RestController
public class testeController {
//
//    @Autowired
//    ApiClient apiClient;
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @PostMapping
//    public ResponseEntity<Object> recebeArquivo(@RequestParam String table_name,
//                                                @RequestParam String table_sys_id,
//                                                @RequestParam MultipartFile uploadFile,
//                                                @RequestHeader("Authorization") String authorization) throws IOException {
//        System.out.println("Authorization: " + authorization);
//        uploadFile.transferTo(Paths.get(uploadFile.getOriginalFilename()));
//        return ResponseEntity.ok(table_name + table_sys_id + uploadFile.getOriginalFilename());
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> bigResponse() throws JsonProcessingException, InterruptedException {
//        char[] data = new char[8042800];
//        Test test = new Test(new String(data));
////        Thread.sleep(50 * 1000);
//        return ResponseEntity.ok(test);
//    }

//    @PostMapping
//    public ResponseEntity<Object> recebeArquivo(@RequestParam MultipartFile file){
//        return ResponseEntity.ok(file.getOriginalFilename());
//    }

//    @PostMapping
//    public ResponseEntity<Object> salva(HttpServletRequest request) throws IOException {
//        InputStream file = request.getInputStream();
//        Files.copy(file, Paths.get( "arquivo"));
//        return ResponseEntity.ok().build();
//    }


}
//    @GetMapping
//    public void teste(HttpServletRequest request){
//        System.out.println("Ta no metodo");
//        System.out.println(request.getRequestURL().toString());
////        System.out.println(httpServletRequest.getQueryString().toString());
//        request.getHeader("Teste");
//        System.out.println("Teste");
//        System.out.println(request.getQueryString());
//    }
