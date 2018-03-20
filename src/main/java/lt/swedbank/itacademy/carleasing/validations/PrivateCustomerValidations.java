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
//    public Integer validateEmail(String email)
//    {
//        EmailValidator emailValidator =EmailValidator.getInstance();
////        if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,})$"))
////        {
////            return 2;
////        }
//        if(emailValidator.isValid(email))
//        {
//            return 2;
//        }
//        return 0;
//    }

    //    private String firstName;
//    private String lastName;
//    private String personalCode;
//    private String email;
//    private String phoneNumber;
//    private String address;

}
