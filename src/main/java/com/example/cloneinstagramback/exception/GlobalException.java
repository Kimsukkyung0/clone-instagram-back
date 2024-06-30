package com.example.cloneinstagramback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException ue, WebRequest req){
        ErrorDetails err = new ErrorDetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgNotValidExceptionHandler(MethodArgumentNotValidException mave, WebRequest req){
        ErrorDetails err = new ErrorDetails(
                mave.getBindingResult().getFieldError().getDefaultMessage()
                , "validation error"
                , LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req){
        ErrorDetails err = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> PostExceptionHandler(PostException pe, WebRequest req){
        ErrorDetails err = new ErrorDetails(pe.getMessage(),req.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}
