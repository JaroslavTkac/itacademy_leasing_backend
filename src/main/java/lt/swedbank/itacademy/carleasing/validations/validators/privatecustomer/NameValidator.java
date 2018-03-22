package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.NameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements
        ConstraintValidator<NameConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return correctName(value);
    }

    @Override
    public void initialize(NameConstraint constraintAnnotation) {

    }

    private boolean correctName(String name) {
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){3,15}$");
    }
}
