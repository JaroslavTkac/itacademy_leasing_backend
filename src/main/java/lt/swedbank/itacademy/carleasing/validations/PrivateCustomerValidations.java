package lt.swedbank.itacademy.carleasing.validations;

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
