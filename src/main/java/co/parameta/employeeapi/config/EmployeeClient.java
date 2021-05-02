package co.parameta.employeeapi.config;

import co.parameta.soap.ws.employee.GetEmployeeByDocumentRequest;
import co.parameta.soap.ws.employee.GetEmployeeByDocumentResponse;
import co.parameta.soap.ws.employee.SaveEmployeeRequest;
import co.parameta.soap.ws.employee.SaveEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class EmployeeClient extends WebServiceGatewaySupport {

  public GetEmployeeByDocumentResponse getEmployeeByDocument(GetEmployeeByDocumentRequest request) {

    return (GetEmployeeByDocumentResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public SaveEmployeeResponse saveEmployee(SaveEmployeeRequest request) {
    return (SaveEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}
