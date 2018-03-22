package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {

    private PrivateCustomer privateCustomer;
    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
    }

    @Test
    public void isValidTestingEmailRegularEmailType() {
        EmailValidator validator = new EmailValidator();
        privateCustomer.setEmail("testemail@gmail.com");
        Assert.assertEquals(true,validator.isValid(privateCustomer.getEmail(), null));
    }
    @Test
    public void isValidTestingEmailWithManyDots() {
        EmailValidator validator = new EmailValidator();
        privateCustomer.setEmail("PetraitisJonaitis.SVARAINIS@email.co.uk");
        Assert.assertEquals(true,validator.isValid(privateCustomer.getEmail(), null));
    }
    @Test
    public void isValidTestingEmailWithoutEta() {
        EmailValidator validator = new EmailValidator();
        privateCustomer.setEmail("Jonas.SVARAINISgmx.com");
        Assert.assertEquals(false,validator.isValid(privateCustomer.getEmail(), null));
    }
    @Test
    public void isValidTestingEmailWithoutInfoAfterEta() {
        EmailValidator validator = new EmailValidator();
        privateCustomer.setEmail("Jonas.SVARAINIS@com");
        Assert.assertEquals(false,validator.isValid(privateCustomer.getEmail(), null));
    }
    @Test
    public void isValidTestingEmailEmpty() {
        EmailValidator validator = new EmailValidator();
        privateCustomer.setEmail("");
        Assert.assertEquals(false,validator.isValid(privateCustomer.getEmail(), null));
    }

}