package lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer.CompanyNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyNameValidator implements
        ConstraintValidator<CompanyNameConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isEmpty() && value.matches("[[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšž" +
                "ÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]\\s]{3,20}");
    }

    @Override
    public void initialize(CompanyNameConstraint constraintAnnotation) {

    }
}
