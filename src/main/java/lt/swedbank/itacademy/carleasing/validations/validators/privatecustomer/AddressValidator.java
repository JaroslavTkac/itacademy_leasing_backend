package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.AddressConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements
        ConstraintValidator<AddressConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isEmpty() && (value.contains(" ") && value.length() > 1);
    }

    @Override
    public void initialize(AddressConstraint constraintAnnotation) {

    }
}
