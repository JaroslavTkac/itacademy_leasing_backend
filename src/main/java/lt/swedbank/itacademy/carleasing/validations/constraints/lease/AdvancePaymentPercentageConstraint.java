package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.AdvancePaymentPercentageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdvancePaymentPercentageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface AdvancePaymentPercentageConstraint {
    String message() default "Invalid advance payment percentage";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
