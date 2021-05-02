package co.parameta.employeeapi.facade;

import co.parameta.employeeapi.config.EmployeeClient;
import co.parameta.employeeapi.exception.NotFoundException;
import co.parameta.employeeapi.facade.mapper.GetEmployeeMapper;
import co.parameta.employeeapi.facade.mapper.SaveEmployeeMapper;
import co.parameta.employeeapi.model.Employee;
import co.parameta.soap.ws.employee.GetEmployeeByDocumentResponse;
import co.parameta.soap.ws.employee.SaveEmployeeResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class EmployeeFacade {

  private final EmployeeClient employeeClient;
  private final GetEmployeeMapper getEmployeeMapper;
  private final SaveEmployeeMapper saveEmployeeMapper;

  public Employee getEmployeeByDocument(final String documentType, final String documentNumber) {
    GetEmployeeByDocumentResponse response =
        employeeClient.getEmployeeByDocument(
            getEmployeeMapper.transformEmployeeByDocumentRequest(documentType, documentNumber));

    if (!HttpStatus.OK.getReasonPhrase().equals(response.getServiceStatus().getStatusCode())) {
      if(HttpStatus.NOT_FOUND.getReasonPhrase().equals(response.getServiceStatus().getStatusCode())){
        throw new NotFoundException();
      }
    }
    return getEmployeeMapper.transformEmployeeResponse(response.getEmployee());
  }

  public void saveEmployee(final Employee request) {
    final SaveEmployeeResponse response =
        employeeClient.saveEmployee(saveEmployeeMapper.transformEmployeeRequest(request));
    if (Objects.nonNull(response)
        && Objects.nonNull(response.getServiceStatus())
        && StringUtils.isNotEmpty(response.getServiceStatus().getStatusCode())) {
      if(!HttpStatus.CREATED.getReasonPhrase().equals(response.getServiceStatus().getStatusCode())){

      }
    }
  }
}
