package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameValidatorTest {

    PrivateCustomer privateCustomer;
    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
    }

    @Test
    public void isValidTestingNameRandomCapitalizedLetters() {
        NameValidator validator = new NameValidator();
        privateCustomer.setFirstName("PetrAitiS");
        assertEquals(true,validator.isValid(privateCustomer.getFirstName(),null));
    }
    @Test
    public void isValidTestingNameWithNumbers() {
        NameValidator validator = new NameValidator();
        privateCustomer.setFirstName("Pe7r4Iti6");
        assertEquals(false,validator.isValid(privateCustomer.getFirstName(),null));
    }
    @Test
    public void isValidTestingNameEmpty() {
        NameValidator validator = new NameValidator();
        privateCustomer.setFirstName("");
        assertEquals(false,validator.isValid(privateCustomer.getFirstName(),null));
    }
}