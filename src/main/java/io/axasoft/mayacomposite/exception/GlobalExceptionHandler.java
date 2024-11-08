package io.axasoft.mayacomposite.exception;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import jakarta.validation.ConstraintViolationException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Aspect
@Component
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ServiceException.class)
    public ProblemDetail handleServiceException(ServiceException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ex.getMessage(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Service Error", localizedMessage, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.INVALID_ARGUMENT,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Invalid Argument", localizedMessage, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.ACCESS_DENIED,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.FORBIDDEN, "Access Denied", localizedMessage, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.CONSTRAINT_VIOLATION,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Constraint Violation", localizedMessage, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.DATABASE_ERROR,
                new Object[]{ex.getMostSpecificCause().getMessage()},
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.CONFLICT, "Database Error", localizedMessage, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        String localizedMessage = messageSource.getMessage(
                ApplicationConstants.RESOURCE_NOT_FOUND,
                null,
                LocaleContextHolder.getLocale()
        );
        return createProblemDetail(HttpStatus.NOT_FOUND, "Resource Not Found", localizedMessage, request);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception ex, WebRequest request) {
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
}
