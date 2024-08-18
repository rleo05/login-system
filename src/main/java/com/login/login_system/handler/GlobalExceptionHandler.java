package com.login.login_system.handler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.login.login_system.exception.ExceptionDetails;
import com.login.login_system.exception.MethodArgumentNotValidDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(MethodArgumentNotValidDetails.builder()
                .timestamp(LocalDate.now())
                .title("MethodArgumentNotValidException")
                .status(HttpStatus.BAD_REQUEST.value())
                .fields(fields)
                .details("Check fields error")
                .fieldsMessage(fieldsMessage)
                .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> handlerBadCredentialException(BadCredentialsException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .details("Invalid credentials")
                .title("BadCredentialsException")
                .timestamp(LocalDate.now())
                .build()
                , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handlerDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("DataIntegrityViolationException")
                .details(exception.getMessage())
                .timestamp(LocalDate.now())
                .status(HttpStatus.CONFLICT.value())
                .build()
                ,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDetails> handlerAuthenticationException(AuthenticationException exception) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("AuthenticationException")
                .details("You are not correctly authenticated")
                .timestamp(LocalDate.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .build()
                ,HttpStatus.UNAUTHORIZED);
    }
}
