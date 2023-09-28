package br.com.attornatus.exceptions;


import br.com.attornatus.exceptions.customException.ConstraintViolationCustomException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity errorHandler404(EntityNotFoundException ex) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body("Error: " + ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity errorHandler400(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();

    List<String> errorMessages = new ArrayList<>();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      errorMessages.add(fieldError.getDefaultMessage());
    }

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(errorMessages);
  }

  @ExceptionHandler(ConstraintViolationCustomException.class)
  public ResponseEntity errorHandler400(ConstraintViolationCustomException ex) {
    return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
  }
}
