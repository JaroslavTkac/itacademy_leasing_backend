package lt.swedbank.itacademy.carleasing.validations;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.constraints.Email;

public class PrivateCustomerValidations extends Validation {
    public Integer validateName(String name)
    {
        if(name.contains("a"))
        {
            return 1;
        }
        return 0;
    }

}
