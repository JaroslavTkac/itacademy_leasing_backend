package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.IntegerMismatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntegerMismatchValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface IntegerMismatchConstraint {
    String message() default "Input mismatch, waiting for Integer input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
