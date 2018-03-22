package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.PersonalCodeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonalCodeValidator implements
        ConstraintValidator<PersonalCodeConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isEmpty() && value.matches("[0-9]+");
    }

    @Override
    public void initialize(PersonalCodeConstraint constraintAnnotation) {

    }
}
