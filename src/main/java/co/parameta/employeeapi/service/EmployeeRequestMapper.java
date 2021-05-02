package co.parameta.employeeapi.service;

import co.parameta.employeeapi.controller.response.EmployeeResponse;
import co.parameta.employeeapi.model.Employee;
import co.parameta.employeeapi.model.TimeOf;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {

  EmployeeResponse transformEmployeeResponse(Employee employee, TimeOf timeInCompany, TimeOf age);
}
