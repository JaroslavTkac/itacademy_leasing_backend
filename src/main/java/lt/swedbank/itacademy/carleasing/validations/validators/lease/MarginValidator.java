package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.MarginConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MarginValidator implements
        ConstraintValidator<MarginConstraint, Float> {

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        return value >= 3.2 && value <= 99;
    }


    @Override
    public void initialize(MarginConstraint constraintAnnotation) {

    }
}
