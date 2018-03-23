package lt.swedbank.itacademy.carleasing.validations.validators.lease;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AssetPriceValidatorTest {

    private Lease lease;
    AssetPriceValidator validator;

    @Before
    public void setUp() throws Exception {
        lease = new Lease();
        validator = new AssetPriceValidator();
    }

    @Test
    public void isValidTestingCorrectMinimalAssetPrice() {
        lease.setAssetPrice(new BigDecimal(5000));
        assertEquals(true, validator.isValid(lease.getAssetPrice(), null));
    }

    @Test
    public void isValidTestingTooSmallAssetPrice() {
        lease.setAssetPrice(new BigDecimal(4999));
        assertEquals(false, validator.isValid(lease.getAssetPrice(), null));
    }

    @Test
    public void isValidTestingNegativeAssetPrice() {
        lease.setAssetPrice(new BigDecimal(-10000));
        assertEquals(false, validator.isValid(lease.getAssetPrice(), null));
    }
}