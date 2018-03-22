package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressValidatorTest {

    private PrivateCustomer privateCustomer;


    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
    }

    @Test
    public void isValidTestingAddressWithCorrectForm() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("Sapiegu g.56");
        assertEquals(true,validator.isValid(privateCustomer.getAddress(), null));
    }

    @Test
    public void isValidTestingAddressBackwards() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("2 Vilniaus g.");
        assertEquals(true,validator.isValid(privateCustomer.getAddress(), null));
    }
    @Test
    public void isValidTestingAddressWithoutStreetNumber() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("Gostauto g.");
        assertEquals(false,validator.isValid(privateCustomer.getAddress(), null));
    }
    @Test
    public void isValidTestingAddressWithoutSpace() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("Gostautog.50");
        assertEquals(false,validator.isValid(privateCustomer.getAddress(), null));
    }
    @Test
    public void isValidTestingAddressWithOnlyStreetNumber() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("50");
        assertEquals(false,validator.isValid(privateCustomer.getAddress(), null));
    }
    @Test
    public void isValidTestingAddressEmpty() {
        AddressValidator validator = new AddressValidator();
        privateCustomer.setAddress("");
        assertEquals(false,validator.isValid(privateCustomer.getAddress(), null));
    }


}