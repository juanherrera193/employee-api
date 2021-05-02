package co.parameta.employeeapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

  @Value("${employee.soap.client-url}")
  private String clientUrl;

  private static final String CONTEXT_PATH = "co.parameta.soap.ws.employee";

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath(CONTEXT_PATH);
    return jaxb2Marshaller;
  }

  @Bean
  public EmployeeClient employeeClient(Jaxb2Marshaller jaxb2Marshaller) {
    EmployeeClient employeeClient = new EmployeeClient();
    employeeClient.setDefaultUri(clientUrl);
    employeeClient.setMarshaller(jaxb2Marshaller);
    employeeClient.setUnmarshaller(jaxb2Marshaller);
    return employeeClient;
  }
}
