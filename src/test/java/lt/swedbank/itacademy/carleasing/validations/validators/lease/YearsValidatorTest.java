package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class YearsValidatorTest {

    private Lease lease;
    YearsValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new YearsValidator();
    }

    @Test
    public void isValidTestingCorrectYears() {
        lease.setYears("2017");
        assertEquals(true, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingMinimalCorrectYears() {
        lease.setYears("1980");
        assertEquals(true, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingMaximalCorrectYears() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        lease.setYears(String.valueOf(cal.get(Calendar.YEAR)));
        assertEquals(true, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingNotValidYearsPast() {
        lease.setYears("1979");
        assertEquals(false, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingNotValidYearsFuture() {
        lease.setYears("2020");
        assertEquals(false, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingCorrectYearsWithRandomChar() {
        lease.setYears("2005a");
        assertEquals(false, validator.isValid(lease.getYears(), null));
    }

    @Test
    public void isValidTestingEmptyYears() {
        lease.setYears("");
        assertEquals(false, validator.isValid(lease.getYears(), null));
    }

}