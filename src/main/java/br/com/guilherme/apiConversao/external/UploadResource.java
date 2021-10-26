package br.com.guilherme.apiConversao.external;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

public interface UploadResource {
    @RequestLine("POST")
    @Headers("Content-Type: multipart/form-data")
    Response uploadFile(@Param("file") MultipartFile file);

    @RequestLine("POST")
    @Headers({"Content-Type: multipart/form-data","Authorization: {authorization}"})
    Response uploadFile(
                        @Param("authorization") String authorization,
                        @Param("table_name") String tableName,
                        @Param("table_sys_id") String tableSys,
                        @Param("uploadFile") MultipartFile file);
}
