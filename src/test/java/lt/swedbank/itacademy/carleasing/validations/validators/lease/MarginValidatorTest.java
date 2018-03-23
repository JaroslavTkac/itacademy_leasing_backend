package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarginValidatorTest {

    private Lease lease;
    MarginValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new MarginValidator();
    }

    @Test
    public void isValidTestingCorrectMargin() {
        lease.setMargin(20);
        assertEquals(true, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingCorrectMinimalMargin() {
        lease.setMargin(3.2f);
        assertEquals(true, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingCorrectMaximalMargin() {
        lease.setMargin(99f);
        assertEquals(true, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingTooSmallMargin() {
        lease.setMargin(3.1f);
        assertEquals(false, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingTooHighMargin() {
        lease.setMargin(99.1f);
        assertEquals(false, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingNegative() {
        lease.setMargin(-5.4f);
        assertEquals(false, validator.isValid(lease.getMargin(), null));
    }

    @Test
    public void isValidTestingRandomHighMargin() {
        lease.setMargin(193f);
        assertEquals(false, validator.isValid(lease.getMargin(), null));
    }
}