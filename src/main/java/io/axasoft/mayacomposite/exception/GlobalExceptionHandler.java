package io.axasoft.mayacomposite.exception;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Aspect
@Component
@Slf4j // Lombok annotation for logging
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Handle MethodArgumentNotValidException for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format(
                        "Field '%s' %s (Rejected value: %s)",
                        fieldError.getField(),
                        getLocalizedErrorMessage(fieldError),
                        fieldError.getRejectedValue()
                ))
                .collect(Collectors.joining(", "));

        // Log the error with the full stack trace
        log.error("Validation error: {}", errors, ex);

        return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error", errors, request);
    }

    @ExceptionHandler(ServiceException.class)
    public ProblemDetail handleServiceException(ServiceException ex, WebRequest request) {
        log.error("Service exception: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ex.getMessage(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Service Error", localizedMessage, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal argument exception: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.INVALID_ARGUMENT,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Invalid Argument", localizedMessage, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.error("Access denied: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.ACCESS_DENIED,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.FORBIDDEN, "Access Denied", localizedMessage, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String violations = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        // Log the error with the full stack trace
        log.error("Constraint violation: {}", violations, ex);

        return createProblemDetail(HttpStatus.BAD_REQUEST, "Constraint Violation", violations, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidationException(ValidationException ex, WebRequest request) {
        log.error("Validation exception: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.CONSTRAINT_VIOLATION,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error", localizedMessage, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Data integrity violation: {}", ex.getMostSpecificCause().getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.DATABASE_ERROR,
                new Object[]{ex.getMostSpecificCause().getMessage()},
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.CONFLICT, "Database Error", localizedMessage, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.error("Resource not found: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.RESOURCE_NOT_FOUND,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.NOT_FOUND, "Resource Not Found", localizedMessage, request);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception ex, WebRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.INTERNAL_SERVER_ERROR,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", localizedMessage, request);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("path", request.getDescription(false));
        return problemDetail;
    }

    private String getLocalizedErrorMessage(FieldError fieldError) {
        return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
    }
}
