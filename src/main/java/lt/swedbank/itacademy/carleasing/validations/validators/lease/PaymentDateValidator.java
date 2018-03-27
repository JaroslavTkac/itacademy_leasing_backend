package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.PaymentDateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PaymentDateValidator implements
        ConstraintValidator<PaymentDateConstraint, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == 15 || value == 30;
    }

    @Override
    public void initialize(PaymentDateConstraint constraintAnnotation) {

    }
}
