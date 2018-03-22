package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.NameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements
        ConstraintValidator<NameConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidName(value);
    }

    @Override
    public void initialize(NameConstraint constraintAnnotation) {

    }

    private boolean isValidName(String name) {
        return name.matches("^[A-Z][a-z]{2}");
    }
}
