package br.com.guilherme.apiConversao.conversor;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class SNMultipart {
    @JsonProperty("Content-Type")
    private String ContentType;
    private String table_name;
    private String table_sys_id;
    private MultipartFile file;


}
