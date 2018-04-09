package lt.swedbank.itacademy.carleasing.beans.documents;

import java.math.BigDecimal;

public class ScheduleOfContributionsPaymentData {

    private BigDecimal notRedeemedAssetValue; //Neispirkta turto verte
    private BigDecimal assetRedemptionFees; //Turto ispirkimo imokos
    private BigDecimal interestPayments; //Palukanu imokos
    private BigDecimal totalMonthlyPaymentValue; // Is Viso


    public ScheduleOfContributionsPaymentData(BigDecimal notRedeemedAssetValue, BigDecimal assetRedemptionFees,
                                              BigDecimal interestPayments, BigDecimal totalMonthlyPaymentValue) {
        setNotRedeemedAssetValue(notRedeemedAssetValue);
        setAssetRedemptionFees(assetRedemptionFees);
        setInterestPayments(interestPayments);
        setTotalMonthlyPaymentValue(totalMonthlyPaymentValue);
    }

    public BigDecimal getNotRedeemedAssetValue() {
        return notRedeemedAssetValue;
    }

    public void setNotRedeemedAssetValue(BigDecimal notRedeemedAssetValue) {
        this.notRedeemedAssetValue = notRedeemedAssetValue;
    }

    public BigDecimal getAssetRedemptionFees() {
        return assetRedemptionFees;
    }

    public void setAssetRedemptionFees(BigDecimal assetRedemptionFees) {
        this.assetRedemptionFees = assetRedemptionFees;
    }

    public BigDecimal getInterestPayments() {
        return interestPayments;
    }

    public void setInterestPayments(BigDecimal interestPayments) {
        this.interestPayments = interestPayments;
    }

    public BigDecimal getTotalMonthlyPaymentValue() {
        return totalMonthlyPaymentValue;
    }

    public void setTotalMonthlyPaymentValue(BigDecimal totalMonthlyPaymentValue) {
        this.totalMonthlyPaymentValue = totalMonthlyPaymentValue;
    }
}
