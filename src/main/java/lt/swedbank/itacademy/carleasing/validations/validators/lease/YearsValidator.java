package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.YearsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class YearsValidator implements
        ConstraintValidator<YearsConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.length() == 4){
            try {
                //User input years
                int actualYears = Integer.parseInt(value);
                //Current years
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                if(!(actualYears >= 1980 && actualYears <= cal.get(Calendar.YEAR))){
                    return false;
                }
            }
            catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }

    @Override
    public void initialize(YearsConstraint constraintAnnotation) {

    }
}
