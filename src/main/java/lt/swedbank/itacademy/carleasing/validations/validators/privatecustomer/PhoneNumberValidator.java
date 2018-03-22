package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.PhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements
        ConstraintValidator<PhoneNumberConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        value = removeIfExistPlus(value);
        return (value.length() == 11 || value.length() == 9) && value.matches("[0-9]+");
    }

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {

    }

    private String removeIfExistPlus(String phoneNumber) {
        if (phoneNumber.charAt(0) == '+') {
            phoneNumber = phoneNumber.substring(1);
        }
        return phoneNumber;
    }
}
