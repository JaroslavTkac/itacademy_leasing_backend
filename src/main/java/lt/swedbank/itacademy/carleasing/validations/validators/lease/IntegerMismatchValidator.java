package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.IntegerMismatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerMismatchValidator implements
        ConstraintValidator<IntegerMismatchConstraint, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        try {
            int results = value;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(IntegerMismatchConstraint constraintAnnotation) {

    }
}
