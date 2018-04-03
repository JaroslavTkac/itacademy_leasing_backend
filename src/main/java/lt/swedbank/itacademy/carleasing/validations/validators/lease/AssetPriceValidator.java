package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.AssetPriceCheck;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssetPriceValidator
        implements ConstraintValidator<AssetPriceCheck, Object> {

    private String field;
    private String constraintField;

    public void initialize(AssetPriceCheck constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.constraintField = constraintAnnotation.constraintField();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object constraintValue = new BeanWrapperImpl(value).getPropertyValue(constraintField);

        float realValue = Float.parseFloat(fieldValue.toString());

        if (constraintValue.toString().equals("Private")) {
            return realValue >= 5000 && realValue <= 10000000;
        }
        return constraintValue.toString().equals("Corporate") && realValue >= 10000 && realValue <= 10000000;
    }
}
