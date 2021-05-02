package co.parameta.employeeapi.facade.mapper;

import co.parameta.employeeapi.model.Employee;
import co.parameta.soap.ws.employee.EmployeeResponse;
import co.parameta.soap.ws.employee.GetEmployeeByDocumentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetEmployeeMapper {

  @Mapping(target = "documentNumber", source = "documentNumber")
  @Mapping(target = "documentType", source = "documentType")
  GetEmployeeByDocumentRequest transformEmployeeByDocumentRequest(
      final String documentType, final String documentNumber);

  Employee transformEmployeeResponse(EmployeeResponse response);
}
