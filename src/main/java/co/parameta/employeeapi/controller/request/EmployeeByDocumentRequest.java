package co.parameta.employeeapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmployeeByDocumentRequest {

  @NotEmpty
  private String documentType;

  @NotEmpty
  private String documentNumber;
}
