package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.CalculationResultValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CalculationResultValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface CalculationResultCheck {
    String message() default "Validation error occurred";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String assetPrice();
    String advancePaymentPercentage();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        CalculationResultCheck[] value();
    }
}
