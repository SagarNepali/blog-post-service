package edu.miu.blogpost.exception.controller;

import edu.miu.blogpost.exception.ApiError;
import edu.miu.blogpost.exception.PostNotFoundException;
import edu.miu.blogpost.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class PostNotFoundExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> postNotFoundExc(PostNotFoundException ex) {
         ApiError error = ApiError.builder()
                .description(ex.getMessage())
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(UserNotFoundException ex){
        ApiError error = ApiError
                .builder()
                .description(ex.getMessage())
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
