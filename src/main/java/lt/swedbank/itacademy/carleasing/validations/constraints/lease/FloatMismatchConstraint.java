package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.FloatMismatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FloatMismatchValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface FloatMismatchConstraint {
    String message() default "Input mismatch, waiting for Float/Double input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
