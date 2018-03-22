package lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer.PersonalCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PersonalCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface PersonalCodeConstraint {
    String message() default "Invalid personal code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
