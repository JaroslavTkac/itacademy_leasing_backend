package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhoneNumberValidatorTest {

    private PrivateCustomer privateCustomer;
    private PhoneNumberValidator validator;

    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
        validator = new PhoneNumberValidator();
    }

    @Test
    public void isValidTestingStandardCorrectNumber() {
        privateCustomer.setPhoneNumber("867326078");
        assertEquals(true, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingCorrectNumberWithCountryCode() {
        privateCustomer.setPhoneNumber("+37065666002");
        assertEquals(true, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingCorrectNumberWithCountryCodeAndWithoutPlusSign() {
        privateCustomer.setPhoneNumber("37065666002");
        assertEquals(true, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingIncorrectTooShortPhoneNumber() {
        privateCustomer.setPhoneNumber("370656660");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingPhoneNumberSizeOfNineWhichStartsFromThree() {
        privateCustomer.setPhoneNumber("370326078");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingPhoneNumberSizeOfNineWhichStartsFromCountryCodeWithPlusSign() {
        privateCustomer.setPhoneNumber("+370326078");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingPhoneNumberWithLetter() {
        privateCustomer.setPhoneNumber("+3706566f002");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingPhoneNumberBeginsNotFromEight() {
        privateCustomer.setPhoneNumber("765666002");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingPhoneNumberBeginsNotFromCountryCode() {
        privateCustomer.setPhoneNumber("+37165666002");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }

    @Test
    public void isValidTestingEmptyPhoneNumber() {
        privateCustomer.setPhoneNumber("");
        assertEquals(false, validator.isValid(privateCustomer.getPhoneNumber(), null));
    }
}