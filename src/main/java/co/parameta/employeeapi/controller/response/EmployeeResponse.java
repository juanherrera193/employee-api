package co.parameta.employeeapi.controller.response;

import co.parameta.employeeapi.model.TimeOf;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponse {

  private String names;
  private String surname;
  private String documentType;
  private String documentNumber;
  private LocalDate dateOfBirth;
  private LocalDate dateOfJoiningCompany;
  private String position;
  private BigDecimal salary;
  private TimeOf timeInCompany;
  private TimeOf age;
}
