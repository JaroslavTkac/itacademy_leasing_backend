package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.validations.constraints.lease.CalculationResultCheck;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CalculationResultValidator
        implements ConstraintValidator<CalculationResultCheck, Object> {

    private String field;
    private String assetPrice;
    private String advancePaymentPercentage;

    public void initialize(CalculationResultCheck constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.assetPrice = constraintAnnotation.assetPrice();
        this.advancePaymentPercentage = constraintAnnotation.advancePaymentPercentage();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object assetPriceValue = new BeanWrapperImpl(value).getPropertyValue(assetPrice);
        Object advancePaymentPercentageValue = new BeanWrapperImpl(value).getPropertyValue(advancePaymentPercentage);

        double realValue = Float.parseFloat(fieldValue.toString());
        double assetPrice = Float.parseFloat(assetPriceValue.toString());
        double percents = Float.parseFloat(advancePaymentPercentageValue.toString());

        if (field.equals("contractFee")) {
            double onePercentOfPrice = assetPrice * 0.01;
            double valueToExpect;

            if (onePercentOfPrice > 200) {
                valueToExpect = onePercentOfPrice;
            } else {
                valueToExpect = 200;
            }

            return Math.abs(realValue - valueToExpect) <= 1;
        }
        if (field.equals("advancePaymentAmount")) {
            double valueToExpect = assetPrice / 100;
            valueToExpect *= percents;

            return Math.abs(realValue - valueToExpect) <= 1;
        }
        return false;
    }
}
