package com.tempolivre.api.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Token inválido ou expirado
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> handleJwtException(JWTVerificationException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Token inválido ou expirado", ex.getMessage());
    }

    // Token genérico inválido (caso você crie a TokenValidationException)
    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<Object> handleCustomTokenException(TokenValidationException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Token inválido", ex.getMessage());
    }

    // Credenciais inválidas no login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", ex.getMessage());
    }

    // Genérica para outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor", ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
