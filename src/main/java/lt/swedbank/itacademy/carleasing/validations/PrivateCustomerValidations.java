package lt.swedbank.itacademy.carleasing.validations;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateCustomerValidations extends Validation {
    public Integer validateFirstName(String name) {
        if (!correctName(name)) {
            return 1;
        }
        return 0;
    }

    public Integer validateLastName(String lastName) {
        if (!correctName(lastName)) {
            return 2;
        }
        return 0;
    }

    private boolean correctName(String name) {
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){3,15}$");
    }


    public Integer validatePersonalCode(String personalCode) {
        if (personalCode.length() != 11) {
            return 3;
        }
        return 0;
    }

    public Integer validateEmail(String email) {
        if (!isValidEmailAddress(email)) {
            return 4;
        }
        return 0;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public Integer validatePhoneNumber(String phoneNumber) {
        phoneNumber = removeIfExistPlus(phoneNumber);
        if (phoneNumber.length() != 11) {
            return 5;
        }
        return 0;
    }

    private String removeIfExistPlus(String phoneNumber) {
        if (phoneNumber.charAt(0) == '+') {
            phoneNumber = phoneNumber.substring(1);
        }
        return phoneNumber;
    }


    public Integer validateAddress(String address) {
        if (address.isEmpty() || !(address.contains(" ") && address.length() > 1)) {
            return 6;
        }
        return 0;
    }
}
