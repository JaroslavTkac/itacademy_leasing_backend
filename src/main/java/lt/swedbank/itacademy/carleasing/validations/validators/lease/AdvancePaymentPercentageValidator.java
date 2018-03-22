package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.AdvancePaymentPercentageConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdvancePaymentPercentageValidator implements
        ConstraintValidator<AdvancePaymentPercentageConstraint, Float> {

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        return value >= 10 && value <= 50;
    }

    @Override
    public void initialize(AdvancePaymentPercentageConstraint constraintAnnotation) {

    }
}
