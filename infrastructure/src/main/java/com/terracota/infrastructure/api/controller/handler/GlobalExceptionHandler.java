package com.terracota.infrastructure.api.controller.handler;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(final Exception ex, final WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse body = ErrorResponse.with(
                ex.getMessage(),
                null,
                status.value(),
                request.getDescription(false));

        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleGenericDomainException(final DomainException ex, final WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse body = ErrorResponse.with(
                ex.getMessage(),
                null,
                status.value(),
                request.getDescription(false));

        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse body = ErrorResponse.with(
                ex.getMessage(),
                null,
                status.value(),
                request.getDescription(false));

        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            final Exception ex,
            Object body,
            final HttpHeaders headers,
            final HttpStatusCode statusCode,
            final WebRequest request
    ) {

        if (body == null){
            body = ErrorResponse.with(
                    ex.getMessage(),
                    null,
                    statusCode.value(),
                    request.getDescription(false));
        } else if (body instanceof String){
            body = ErrorResponse.with(
                    (String) body,
                    null,
                    statusCode.value(),
                    request.getDescription(false));
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
