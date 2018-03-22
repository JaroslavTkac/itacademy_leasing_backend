package lt.swedbank.itacademy.carleasing.validations.validators.privatecustomer;

        import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
        import org.junit.Before;
        import org.junit.Test;

        import static org.junit.Assert.assertEquals;

public class PhoneNumberValidatorTest {
    PrivateCustomer privateCustomer;

    @Before
    public void setUp() throws Exception {
        privateCustomer = new PrivateCustomer();
    }

    @Test
    public void isValid() {
        PhoneNumberValidator validator = new PhoneNumberValidator();
        privateCustomer.setPhoneNumber("867326078");
        assertEquals(true,validator.isValid(privateCustomer.getPhoneNumber(),null));

    }
}