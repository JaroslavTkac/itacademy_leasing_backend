package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.AssetPriceConstraint;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class AssetPriceValidator implements
        ConstraintValidator<AssetPriceConstraint, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value.compareTo(new BigDecimal("5000")) >= 0 &&
                value.compareTo(new BigDecimal("10000000")) <= 0;
    }

    @Override
    public void initialize(AssetPriceConstraint constraintAnnotation) {

    }
}
