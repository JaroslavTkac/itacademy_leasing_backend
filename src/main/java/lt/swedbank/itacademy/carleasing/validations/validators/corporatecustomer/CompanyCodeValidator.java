package lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer.CompanyCodeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyCodeValidator implements
        ConstraintValidator<CompanyCodeConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isEmpty() && value.matches("[0-9]+") && value.length() == 9;
    }

    @Override
    public void initialize(CompanyCodeConstraint constraintAnnotation) {

    }
}
