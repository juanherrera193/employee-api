package co.parameta.employeeapi.service;

import co.parameta.employeeapi.controller.response.EmployeeResponse;
import co.parameta.employeeapi.model.Employee;

public interface EmployeeService {

  EmployeeResponse getEmployeeByDocument(String documentType, String documentNumber);

  void saveEmployee(Employee request);
}
