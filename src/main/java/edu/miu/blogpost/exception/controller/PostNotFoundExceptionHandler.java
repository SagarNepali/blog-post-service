package edu.miu.blogpost.exception.controller;

import edu.miu.blogpost.exception.ApiError;
import edu.miu.blogpost.exception.PostNotFoundException;
import edu.miu.blogpost.exception.UserNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException ex){
        ApiError error = ApiError
                .builder()
                .description(ex.getMessage())
                .message(ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField()+" -> "+fieldError.getDefaultMessage()).collect(Collectors.joining(",")))
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> FeignException(FeignException ex){
        ApiError error = ApiError
                .builder()
                .description(ex.getMessage())
                .message(ex.status()==404?"User not found":"Internal Server Error")
                .statusCode(HttpStatus.resolve(ex.status()).toString())
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
