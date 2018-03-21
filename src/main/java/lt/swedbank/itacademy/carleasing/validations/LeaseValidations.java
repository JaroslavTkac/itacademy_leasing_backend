package lt.swedbank.itacademy.carleasing.validations;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class LeaseValidations extends Validation{

//TODO paklausti del into kai paduodam stringa
    //TODO paklausti del null
    public Integer validateAssetType(String assetType) {
        if(!assetType.equals("Vehicle")){
            return 1;
        }
        return 0;
    }

    public Integer validateCarBrand(String carBrand) {
        if(carBrand.isEmpty()){
            return 1;
        }
        return 0;
    }

    public Integer validateCarModel(String carModel) {
        if(carModel.isEmpty()){
            return 2;
        }
        return 0;
    }

    public Integer validateYears(String years) {
        if(years.length() == 4){
            try {
                //User input years
                int actualYears = Integer.parseInt(years);
                //Current years
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                if(!(actualYears >= 1980 && actualYears <= cal.get(Calendar.YEAR))){
                    return 3;
                }
            }
            catch (Exception e){
                return 3;
            }
        }
        else{
            return 3;
        }
        return 0;
    }

    public Integer validateEnginePower(int enginePower) {
        if(!(enginePower > 40 && enginePower <= 2000)){
            return 4;
        }
        return 0;
    }

    public Integer validateAssetPrice(BigDecimal assetPrice) {
        if(assetPrice.compareTo(new BigDecimal("5000")) < 0){
            return 5;
        }
        if(assetPrice.compareTo(new BigDecimal("10000000")) > 0){
            return 5;
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

    public Integer validateAdvancePaymentPercentage(float advancePaymentPercentage) {
        if(!(advancePaymentPercentage >= 10 && advancePaymentPercentage <= 50)){
            return 7;
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

    public Integer validateLeasePeriod(int leasePeriod) {
        if(!(leasePeriod >= 6 && leasePeriod <= 84)){
            return 9;
        }
        if(leasePeriod % 6 != 0){
            return 9;
        }
        return 0;
    }

    public Integer validateMargin(float margin) {
        if(!(margin >= 3.2 && margin <= 100)){
            return 10;
        }
        return 0;
    }

    public Integer validatePaymentDate(int paymentDate) {
        if(!(paymentDate == 15 || paymentDate == 30)) {
            return 11;
        }
        return 0;
    }


}
