package lt.swedbank.itacademy.carleasing.validations.constraints.lease;

import lt.swedbank.itacademy.carleasing.validations.validators.lease.AssetPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AssetPriceValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface AssetPriceCheck {

    String message() default "Invalid asset price";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String constraintField();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        AssetPriceCheck[] value();
    }
}
