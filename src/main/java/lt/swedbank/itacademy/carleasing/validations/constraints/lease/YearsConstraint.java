package lt.swedbank.itacademy.carleasing.validations.constraints.lease;


import lt.swedbank.itacademy.carleasing.validations.validators.lease.YearsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface YearsConstraint {
    String message() default "Invalid car years";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
