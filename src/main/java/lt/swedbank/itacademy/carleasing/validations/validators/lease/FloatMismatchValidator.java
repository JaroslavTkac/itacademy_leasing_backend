package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.FloatMismatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FloatMismatchValidator implements
        ConstraintValidator<FloatMismatchConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Float.parseFloat(value);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(FloatMismatchConstraint constraintAnnotation) {

    }
}
