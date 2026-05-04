package br.com.boni.spring_boot_essentials.handler;

import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.ErrorResponse;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExeception(NotFoundException ex){
        ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND.value()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExeception(Exception ex){
        ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BadrequestExeption.class)
    public ResponseEntity<ErrorResponse> handleExeception(BadrequestExeption ex){
        ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
