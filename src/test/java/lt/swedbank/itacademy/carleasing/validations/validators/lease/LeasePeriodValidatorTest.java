package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeasePeriodValidatorTest {

    private Lease lease;
    LeasePeriodValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new LeasePeriodValidator();
    }

    @Test
    public void isValidTestingCorrectLeasePeriod() {
        lease.setLeasePeriod(12);
        assertEquals(true, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingCorrectMinimalLeasePeriod() {
        lease.setLeasePeriod(6);
        assertEquals(true, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingCorrectMaximalLeasePeriod() {
        lease.setLeasePeriod(84);
        assertEquals(true, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingTooSmallLeasePeriod() {
        lease.setLeasePeriod(5);
        assertEquals(false, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingTooHighLeasePeriod() {
        lease.setLeasePeriod(85);
        assertEquals(false, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingTooHighLeasePeriodWhichIsSixCommonMultiple() {
        lease.setLeasePeriod(90);
        assertEquals(false, validator.isValid(lease.getLeasePeriod(), null));
    }

    @Test
    public void isValidTestingNegativeLeasePeriod() {
        lease.setLeasePeriod(-6);
        assertEquals(false, validator.isValid(lease.getLeasePeriod(), null));
    }
}