package co.parameta.employeeapi.exception;

public class NotFoundException extends RuntimeException {

  private static final String EMPLOYEE_NOT_FOUND = "The employee not found";

  public NotFoundException() {
    super(EMPLOYEE_NOT_FOUND);
  }
}
