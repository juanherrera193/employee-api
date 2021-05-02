package co.parameta.employeeapi.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeeExceptionHandler {

  private static final String ERROR_MESSAGE = "Rejected: Field (%s) Value (%s) ";

  @ExceptionHandler(BindException.class)
  public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      BindException ex) {
    String errorMessage = ex.getMessage();
    if (!CollectionUtils.isEmpty(ex.getFieldErrors())) {
      errorMessage = fieldErrors(ex.getFieldErrors());
    }
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public final ResponseEntity<ErrorResponse> handleInvalidFormatException(
          InvalidFormatException ex) {
    String errorMessage = ex.getMessage();
    if(!CollectionUtils.isEmpty(ex.getPath())){
      errorMessage = fieldPathErrors(ex.getPath(), ex.getValue().toString());
    }
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BusinessException.class)
  public final ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    ErrorResponse error =
        new ErrorResponse(HttpStatus.PRECONDITION_REQUIRED.getReasonPhrase(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.PRECONDITION_REQUIRED);
  }

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
    ErrorResponse error =
        new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  private String fieldErrors(List<FieldError> fieldErrorList) {
    return fieldErrorList.stream()
        .map(
            fieldError ->
                String.format(ERROR_MESSAGE, fieldError.getField(), fieldError.getRejectedValue()))
        .collect(Collectors.joining());
  }

  private String fieldPathErrors(List<JsonMappingException.Reference> referenceList, String value) {
    return referenceList.stream()
            .map(
                    fieldError ->
                            String.format(ERROR_MESSAGE, fieldError.getFieldName(), value))
            .collect(Collectors.joining());
  }
}
