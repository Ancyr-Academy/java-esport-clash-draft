package fr.ancyracademy.esportclash.spring.exceptions;

import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    var formattableException = new FormattableException(ex.getMessage(), LocalDateTime.now());
    return super.handleExceptionInternal(ex, formattableException.toBody(), headers, statusCode, request);
  }

  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<?> handleNotFoundException(NotFoundException e, WebRequest request) {
    return handleExceptionInternal(
        e,
        null,
        new HttpHeaders(),
        HttpStatus.NOT_FOUND,
        request
    );
  }
}
