package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameValidatorTest {

    private PrivateCustomer privateCustomer;
    private NameValidator validator;

    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
        validator = new NameValidator();
    }

    @Test
    public void isValidTestingNameRandomCapitalizedLetters() {
        privateCustomer.setFirstName("PetrAitiS");
        assertEquals(true, validator.isValid(privateCustomer.getFirstName(), null));
    }

    @Test
    public void isValidTestingNameWithLithuanianLetters() {
        privateCustomer.setFirstName("Švytautaus");
        assertEquals(true, validator.isValid(privateCustomer.getFirstName(), null));
    }

    @Test
    public void isValidTestingNameWithNumbers() {
        privateCustomer.setFirstName("Pe7r4Iti6");
        assertEquals(false, validator.isValid(privateCustomer.getFirstName(), null));
    }

    @Test
    public void isValidTestingNameEmpty() {
        privateCustomer.setFirstName("");
        assertEquals(false, validator.isValid(privateCustomer.getFirstName(), null));
    }
}