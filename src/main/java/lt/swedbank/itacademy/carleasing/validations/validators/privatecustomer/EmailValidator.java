package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^.+@.+\\..+$");
    }

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }

}
