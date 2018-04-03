package lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer;

import lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer.CompanyNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyNameConstraint {
    String message() default "Invalid company name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
