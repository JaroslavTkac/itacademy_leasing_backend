package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressValidatorTest {

    private PrivateCustomer privateCustomer;
    private AddressValidator validator;

    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
        validator = new AddressValidator();
    }

    @Test
    public void isValidTestingAddressWithCorrectForm() {
        privateCustomer.setAddress("Sapiegu g.56");
        assertEquals(true, validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressBackwards() {
        privateCustomer.setAddress("2 Vilniaus g.");
        assertEquals(true, validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressWithoutStreetNumber() {
        privateCustomer.setAddress("Gostauto g.");
        assertEquals(false, validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressWithoutSpace() {
        privateCustomer.setAddress("Gostautog.50");
        assertEquals(false, validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressWithOnlyStreetNumber() {
        privateCustomer.setAddress("50");
        assertEquals(false, validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressEmpty() {
        privateCustomer.setAddress("");
        assertEquals(false, validator.isValid(privateCustomer.getAddress(), null));
    }


}