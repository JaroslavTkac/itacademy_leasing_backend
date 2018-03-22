package lt.swedbank.itacademy.carleasing.validations;

import java.math.BigDecimal;

public class LeaseValidations extends Validation{

    public Integer validateAssetType(String assetType) {
        if(!assetType.equals("Vehicle")){
            return 1;
        }
        return 0;
    }


    public Integer validateContractFee(BigDecimal contractFee, BigDecimal assetPrice) {
        BigDecimal onePercentOfPrice = assetPrice.multiply(new BigDecimal("0.01"));
        BigDecimal valueToExpect = BigDecimal.ZERO;

        if(onePercentOfPrice.compareTo(new BigDecimal("200")) > 0){
            valueToExpect = onePercentOfPrice;
        }
        else{
            valueToExpect = new BigDecimal("200");
        }

        if(!(valueToExpect.compareTo(contractFee) == 0)){
            return 6;
        }

        return 0;
    }


    public Integer validateAdvancePaymentAmount(BigDecimal advancePaymentAmount, float advancePaymentPercentage, BigDecimal assetPrice) {
        BigDecimal valueToExpect = assetPrice.divide(new BigDecimal("100"));
        valueToExpect = valueToExpect.multiply(new BigDecimal(advancePaymentPercentage));

        if(!(advancePaymentAmount.compareTo(valueToExpect) == 0)){
            return 8;
        }

        return 0;
    }

}
