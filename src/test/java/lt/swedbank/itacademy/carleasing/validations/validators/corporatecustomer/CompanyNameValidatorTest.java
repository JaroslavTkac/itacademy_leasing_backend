package lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyNameValidatorTest {

    private CorporateCustomer corporateCustomer;
    private CompanyNameValidator validator;

    @Before
    public void setUp() throws Exception {
        corporateCustomer = new CorporateCustomer();
        validator = new CompanyNameValidator();
    }

    @Test
    public void isValidTestingCompanyNameWithCorrectInputWithoutSpace() {
        corporateCustomer.setCompanyName("Swedbank");
        assertEquals(true, validator.isValid(corporateCustomer.getCompanyName(), null));
    }

    @Test
    public void isValidTestingCompanyNameWithCorrectInputWithSpace() {
        corporateCustomer.setCompanyName("AB Swedbank");
        assertEquals(true, validator.isValid(corporateCustomer.getCompanyName(), null));
    }

    @Test
    public void isValidTestingCompanyNameWithTooSmallLength() {
        corporateCustomer.setCompanyName("KE");
        assertEquals(false, validator.isValid(corporateCustomer.getCompanyName(), null));
    }

    @Test
    public void isValidTestingCompanyNameWithTooBigLength() {
        corporateCustomer.setCompanyName("UAB ALTAS UAB ALTAS UAB ALTAS UAB ALTAS UAB ALTAS UAB ALTAS");
        assertEquals(false, validator.isValid(corporateCustomer.getCompanyName(), null));
    }
}