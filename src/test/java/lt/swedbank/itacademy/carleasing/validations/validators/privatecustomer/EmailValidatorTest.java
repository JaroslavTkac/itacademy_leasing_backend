package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {

    private PrivateCustomer privateCustomer;
    private EmailValidator validator;

    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
        validator = new EmailValidator();
    }

    @Test
    public void isValidTestingEmailRegularEmailType() {
        privateCustomer.setEmail("testemail@gmail.com");
        Assert.assertEquals(true, validator.isValid(privateCustomer.getEmail(), null));
    }

    @Test
    public void isValidTestingEmailWithManyDots() {
        privateCustomer.setEmail("PetraitisJonaitis.SVARAINIS@email.co.uk");
        Assert.assertEquals(true, validator.isValid(privateCustomer.getEmail(), null));
    }

    @Test
    public void isValidTestingEmailWithoutEta() {
        privateCustomer.setEmail("Jonas.SVARAINISgmx.com");
        Assert.assertEquals(false, validator.isValid(privateCustomer.getEmail(), null));
    }

    @Test
    public void isValidTestingEmailWithoutInfoAfterEta() {
        privateCustomer.setEmail("Jonas.SVARAINIS@com");
        Assert.assertEquals(false, validator.isValid(privateCustomer.getEmail(), null));
    }

    @Test
    public void isValidTestingEmailEmpty() {
        privateCustomer.setEmail("");
        Assert.assertEquals(false, validator.isValid(privateCustomer.getEmail(), null));
    }

}