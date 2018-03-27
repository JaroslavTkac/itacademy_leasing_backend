package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentDateValidatorTest {

    private Lease lease;
    PaymentDateValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new PaymentDateValidator();
    }

    @Test
    public void isValidTestingCorrectPaymentDate() {
        lease.setPaymentDate(15);
        assertEquals(true, validator.isValid(lease.getPaymentDate(), null));
    }

    @Test
    public void isValidTestingSecondCorrectPaymentDate() {
        lease.setPaymentDate(30);
        assertEquals(true, validator.isValid(lease.getPaymentDate(), null));
    }

    @Test
    public void isValidTestingNegativePaymentDate() {
        lease.setPaymentDate(-30);
        assertEquals(false, validator.isValid(lease.getPaymentDate(), null));
    }

    @Test
    public void isValidTestingIncorrectPaymentDate() {
        lease.setPaymentDate(31);
        assertEquals(false, validator.isValid(lease.getPaymentDate(), null));
    }
}