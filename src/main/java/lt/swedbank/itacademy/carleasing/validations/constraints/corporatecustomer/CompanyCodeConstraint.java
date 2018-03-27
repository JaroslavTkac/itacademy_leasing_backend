package lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer;


import lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer.CompanyCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface CompanyCodeConstraint {
    String message() default "Invalid advance payment percentage";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
