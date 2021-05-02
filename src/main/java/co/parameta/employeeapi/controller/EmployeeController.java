package co.parameta.employeeapi.controller;

import co.parameta.employeeapi.controller.request.EmployeeByDocumentRequest;
import co.parameta.employeeapi.controller.response.EmployeeResponse;
import co.parameta.employeeapi.model.Employee;
import co.parameta.employeeapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<EmployeeResponse> getEmployeeByDocument(
      @Valid @NotNull final EmployeeByDocumentRequest request) {
    return new ResponseEntity<>(
        employeeService.getEmployeeByDocument(
            request.getDocumentType(), request.getDocumentNumber()),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody final Employee request) {
    employeeService.saveEmployee(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
