package co.parameta.employeeapi.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String exception) {
    super(exception);
  }
}
