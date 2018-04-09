package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.EnginePowerConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnginePowerValidator implements
        ConstraintValidator<EnginePowerConstraint, Integer> {


    @Override
    public void initialize(EnginePowerConstraint constraintAnnotation) {
        constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 50 && value <= 1500;
    }
}
