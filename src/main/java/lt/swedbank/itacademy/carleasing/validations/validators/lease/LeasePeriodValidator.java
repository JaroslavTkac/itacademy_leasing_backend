package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.LeasePeriodConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LeasePeriodValidator implements
        ConstraintValidator<LeasePeriodConstraint, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return (value >= 6 && value <= 84) && value % 6 == 0;
    }

    @Override
    public void initialize(LeasePeriodConstraint constraintAnnotation) {

    }
}
