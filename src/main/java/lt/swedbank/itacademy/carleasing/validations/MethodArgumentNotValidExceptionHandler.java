package lt.swedbank.itacademy.carleasing.validations;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ObjectError> objectErrors = result.getAllErrors();
        return processFieldErrors(fieldErrors, objectErrors);
    }

    private Error processFieldErrors(List<FieldError> fieldErrors, List<ObjectError> objectErrors) {
        Error error = new Error(BAD_REQUEST.value(), "Validation error");
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : fieldErrors) {
                error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue(),
                        true, null, null, "Error occurred");
            }
        }
        if(fieldErrors.size() == 0 && objectErrors.size() > 0){
            for (ObjectError objectError : objectErrors) {
                error.addFieldError(objectError.getObjectName(), objectError.getDefaultMessage());
            }
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        void addFieldError(String field, String errorMessage, Object rejectedValue, boolean bindingFailure,
                            String[] codes, Object[] arguments, String defaultMessage) {
            FieldError error = new FieldError(field, errorMessage, rejectedValue, bindingFailure, codes, arguments, defaultMessage);
            fieldErrors.add(error);
        }

        void addFieldError(String field, String errorMessage) {
            FieldError error = new FieldError(field, errorMessage, "Error occurred");
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }
}
