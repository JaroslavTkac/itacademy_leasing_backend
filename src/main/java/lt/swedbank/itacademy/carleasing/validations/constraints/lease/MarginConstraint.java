package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.MarginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MarginValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface MarginConstraint {
    String message() default "Invalid margin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
