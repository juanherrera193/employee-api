package co.parameta.employeeapi.facade.mapper;

import co.parameta.employeeapi.model.Employee;
import co.parameta.soap.ws.employee.EmployeeRequest;
import co.parameta.soap.ws.employee.SaveEmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaveEmployeeMapper {

  @Mapping(target = "employee", expression = "java(transformRequestToModel(request))")
  SaveEmployeeRequest transformEmployeeRequest(Employee request);

  EmployeeRequest transformRequestToModel(Employee request);
}
