package lt.swedbank.itacademy.carleasing.beans.documents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @Test
    public void formatPhoneNumberTestingWithCorrectInput() {
        customer.setPhoneNumber("+37065666000");
        assertEquals("+37065666000", customer.formatPhoneNumber(customer.getPhoneNumber()));
    }

    @Test
    public void formatPhoneNumberTestingWithoutPlusSign() {
        customer.setPhoneNumber("37065666000");
        assertEquals("+37065666000", customer.formatPhoneNumber(customer.getPhoneNumber()));
    }

    @Test
    public void formatPhoneNumberTestingWithoutCountryCode() {
        customer.setPhoneNumber("865666000");
        assertEquals("+37065666000", customer.formatPhoneNumber(customer.getPhoneNumber()));
    }
}