package co.parameta.employeeapi.service;

import co.parameta.employeeapi.controller.response.EmployeeResponse;
import co.parameta.employeeapi.exception.BusinessException;
import co.parameta.employeeapi.facade.EmployeeFacade;
import co.parameta.employeeapi.model.Employee;
import co.parameta.employeeapi.model.TimeOf;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private static final String BUSINESS_AGE_EXCEPTION_MSG = "The employee must be of legal age";

  @Value("${employee.min-age}")
  private int minAge;

  private final EmployeeFacade employeeFacade;
  private final EmployeeRequestMapper employeeRequestMapper;

  @Override
  public EmployeeResponse getEmployeeByDocument(String documentType, String documentNumber) {
    final Employee employee = employeeFacade.getEmployeeByDocument(documentType, documentNumber);


    return employeeRequestMapper.transformEmployeeResponse(
        employee,
        calculatePeriod(employee.getDateOfJoiningCompany()),
        calculatePeriod(employee.getDateOfBirth()));
  }

  @Override
  public void saveEmployee(Employee request) {
    Period period = Period.between(request.getDateOfBirth(), LocalDate.now());
    if (period.getYears() < minAge) {
      throw new BusinessException(BUSINESS_AGE_EXCEPTION_MSG);
    }
    employeeFacade.saveEmployee(request);
  }

  private TimeOf calculatePeriod(LocalDate localDateFrom) {
    Period period = Period.between(localDateFrom, LocalDate.now());
    return new TimeOf(period.getYears(), period.getMonths(), period.getDays());
  }
}
