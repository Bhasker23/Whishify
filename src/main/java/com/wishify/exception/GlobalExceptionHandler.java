package com.wishify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandle(UserException userException, WebRequest wr) {

        ErrorDetails ex = new ErrorDetails();
        ex.setMessage(userException.getMessage());
        ex.setDesc(wr.getDescription(false));
        ex.setDate(LocalDateTime.now());

        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(WishListException.class)
    public ResponseEntity<ErrorDetails> wishListExceptionHandler(WishListException item, WebRequest wr){

        ErrorDetails ex = new ErrorDetails();
        ex.setMessage(item.getMessage());
        ex.setDesc(wr.getDescription(false));
        ex.setDate(LocalDateTime.now());

        return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception exception, WebRequest wr){

        ErrorDetails ex = new ErrorDetails();
        ex.setMessage(exception.getMessage());
        ex.setDesc(wr.getDescription(false));
        ex.setDate(LocalDateTime.now());

        return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
    }
}
