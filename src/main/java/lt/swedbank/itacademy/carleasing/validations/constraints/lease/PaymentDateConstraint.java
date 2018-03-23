package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.PaymentDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface PaymentDateConstraint {
    String message() default "Invalid payment date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
