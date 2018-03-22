package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidEmailAddress(value);
    }

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }
    private boolean isValidEmailAddress(String email) {
        String ePattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
