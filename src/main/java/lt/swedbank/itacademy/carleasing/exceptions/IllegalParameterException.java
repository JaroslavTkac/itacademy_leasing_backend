package lt.swedbank.itacademy.carleasing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalParameterException extends RuntimeException {

    public String message;

    public IllegalParameterException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
