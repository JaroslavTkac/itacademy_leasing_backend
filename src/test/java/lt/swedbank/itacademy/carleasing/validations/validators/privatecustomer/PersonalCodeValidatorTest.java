package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonalCodeValidatorTest {

    PrivateCustomer privateCustomer;
    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
    }

    @Test
    public void isValidTestingPersonalCodeCorrect() {
        PersonalCodeValidator validator = new PersonalCodeValidator();
        privateCustomer.setPersonalCode("39706270150");
        assertEquals(true,validator.isValid(privateCustomer.getPersonalCode(),null));
    }
    @Test
    public void isValidTestingPersonalCodeTooShort() {
        PersonalCodeValidator validator = new PersonalCodeValidator();
        privateCustomer.setPersonalCode("397062701");
        assertEquals(false,validator.isValid(privateCustomer.getPersonalCode(),null));
    }
    @Test
    public void isValidTestingPersonalCodeContainsLetters() {
        PersonalCodeValidator validator = new PersonalCodeValidator();
        privateCustomer.setPersonalCode("397062b015a");
        assertEquals(false,validator.isValid(privateCustomer.getPersonalCode(),null));
    }
    @Test
    public void isValidTestingPersonalCodeEmpty() {
        PersonalCodeValidator validator = new PersonalCodeValidator();
        privateCustomer.setPersonalCode("");
        assertEquals(false,validator.isValid(privateCustomer.getPersonalCode(),null));
    }
}