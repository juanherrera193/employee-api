package co.parameta.employeeapi.exception;

public class BusinessException extends RuntimeException {

  public BusinessException(String exception) {
    super(exception);
  }
}
