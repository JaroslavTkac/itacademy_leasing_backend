package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdvancePaymentPercentageValidatorTest {

    private Lease lease;
    AdvancePaymentPercentageValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new AdvancePaymentPercentageValidator();
    }

    @Test
    public void isValidTestingCorrectAmountOfPercents() {
        lease.setAdvancePaymentPercentage(20);
        assertEquals(true, validator.isValid(lease.getAdvancePaymentPercentage(), null));
    }

    @Test
    public void isValidTestingOverValidAmountOfPercents() {
        lease.setAdvancePaymentPercentage(51);
        assertEquals(false, validator.isValid(lease.getAdvancePaymentPercentage(), null));
    }

    @Test
    public void isValidTestingTooSmallAmountOfPercents() {
        lease.setAdvancePaymentPercentage(5);
        assertEquals(false, validator.isValid(lease.getAdvancePaymentPercentage(), null));
    }

    @Test
    public void isValidTestingNegativeAmountOfPercents() {
        lease.setAdvancePaymentPercentage(-10);
        assertEquals(false, validator.isValid(lease.getAdvancePaymentPercentage(), null));
    }


}