package it.corsojava.progettodonazioni;

import it.corsojava.progettodonazioni.exception.ApiErrorResponse;
import it.corsojava.progettodonazioni.exception.BadCredentialsException;
import it.corsojava.progettodonazioni.exception.EntityAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR",
                "Uno o più campi non sono validi", request, errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            EntityNotFoundException e, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", e.getMessage(), request, null);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExists(
            EntityAlreadyExistException e, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, "ALREADY_EXISTS", e.getMessage(), request, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("Violazione vincolo DB", e); // dettaglio SOLO nei log
        return build(HttpStatus.CONFLICT, "DATA_CONFLICT",
                "Dato duplicato o vincolo violato", request, null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentials(
            BadCredentialsException e, HttpServletRequest request) {
        return build(HttpStatus.UNAUTHORIZED, "BAD_CREDENTIALS",
                "Credenziali non valide", request, null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(
            AccessDeniedException e, HttpServletRequest request) {
        return build(HttpStatus.FORBIDDEN, "ACCESS_DENIED",
                "Non hai i permessi per questa operazione", request, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception e, HttpServletRequest request) {
        log.error("Errore non gestito", e); // stack trace completo solo lato server
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
                "Si è verificato un errore interno", request, null);
    }

    private ResponseEntity<ApiErrorResponse> build(
            HttpStatus status, String error, String message,
            HttpServletRequest request, Map<String, String> validationErrors) {

        ApiErrorResponse body = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(request.getRequestURI())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(status).body(body);
    }
}