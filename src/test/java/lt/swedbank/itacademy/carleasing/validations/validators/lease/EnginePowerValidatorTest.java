package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnginePowerValidatorTest {

    private Lease lease;
    EnginePowerValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new EnginePowerValidator();
    }

    @Test
    public void isValidTestingCorrectEnginePower() {
        lease.setEnginePower(110);
        assertEquals(true, validator.isValid(lease.getEnginePower(), null));
    }

    @Test
    public void isValidTestingIncorrectTooSmallEnginePower() {
        lease.setEnginePower(20);
        assertEquals(false, validator.isValid(lease.getEnginePower(), null));
    }

    @Test
    public void isValidTestingNegativeEnginePower() {
        lease.setEnginePower(-77);
        assertEquals(false, validator.isValid(lease.getEnginePower(), null));
    }

    @Test
    public void isValidTestingTooHighEnginePower() {
        lease.setEnginePower(2000);
        assertEquals(false, validator.isValid(lease.getEnginePower(), null));
    }
}