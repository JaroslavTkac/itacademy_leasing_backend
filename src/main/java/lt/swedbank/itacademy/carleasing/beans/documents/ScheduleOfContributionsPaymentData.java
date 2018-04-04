package lt.swedbank.itacademy.carleasing.beans.documents;

import java.math.BigDecimal;

public class ScheduleOfContributionsPaymentData {

    private BigDecimal notRedeemedAssetValue;
    private BigDecimal assetRedemptionFees;
    private BigDecimal interestPayments;
    private BigDecimal totalMonthlyPaymentValue;


    public ScheduleOfContributionsPaymentData(BigDecimal notRedeemedAssetValue, BigDecimal assetRedemptionFees,
                                              BigDecimal interestPayments, BigDecimal totalMonthlyPaymentValue) {
        setAssetRedemptionFees(notRedeemedAssetValue);
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
