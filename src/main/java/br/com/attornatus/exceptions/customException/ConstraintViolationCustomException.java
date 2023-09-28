package br.com.attornatus.exceptions.customException;

public class ConstraintViolationCustomException extends RuntimeException {
  public ConstraintViolationCustomException(String mensagem) {
    super(mensagem);
  }
}
