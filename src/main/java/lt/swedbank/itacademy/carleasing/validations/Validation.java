package lt.swedbank.itacademy.carleasing.validations;

import java.util.ArrayList;
import java.util.List;

public class Validation {

    public List<Integer> checkForActualError(List<Integer> errorCodes){
        List<Integer> errors = new ArrayList<>();

        for (Integer error: errorCodes) {
            if(error != 0){
                errors.add(error);
            }
        }
        return errors;
    }
}
