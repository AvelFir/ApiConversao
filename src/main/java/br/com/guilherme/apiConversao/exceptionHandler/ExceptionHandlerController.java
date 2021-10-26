package br.com.guilherme.apiConversao.exceptionHandler;

import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handle(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UrlPadraoException.class)
    public ResponseEntity<ExceptionResponse> handle(UrlPadraoException e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException e) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(e.contentUTF8(),httpHeaders,HttpStatus.valueOf(e.status()));
    }

}
