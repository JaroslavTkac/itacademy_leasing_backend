package lt.swedbank.itacademy.carleasing.validations.validators.corporatecustomer;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyCodeValidatorTest {

    private CorporateCustomer corporateCustomer;
    private CompanyCodeValidator validator;

    @Before
    public void setUp() throws Exception {
        corporateCustomer = new CorporateCustomer();
        validator = new CompanyCodeValidator();
    }

    @Test
    public void isValidTestingCompanyCodeWithCorrectInput() {
        corporateCustomer.setCompanyCode("391205819");
        assertEquals(true, validator.isValid(corporateCustomer.getCompanyCode(), null));
    }

    @Test
    public void isValidTestingCompanyCodeWithRandomCharacter() {
        corporateCustomer.setCompanyCode("391205c19");
        assertEquals(false, validator.isValid(corporateCustomer.getCompanyCode(), null));
    }

    @Test
    public void isValidTestingEmptyCompanyCode() {
        corporateCustomer.setCompanyCode("");
        assertEquals(false, validator.isValid(corporateCustomer.getCompanyCode(), null));
    }
}