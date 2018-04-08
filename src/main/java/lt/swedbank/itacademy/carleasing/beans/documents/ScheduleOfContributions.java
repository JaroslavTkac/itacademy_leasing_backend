package lt.swedbank.itacademy.carleasing.beans.documents;


import lt.swedbank.itacademy.carleasing.validations.constraints.lease.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AssetPriceCheck.List({
        @AssetPriceCheck(
                field = "assetPrice",
                constraintField = "leaseType",
                message = "Invalid Asset price"
        )
})
@CalculationResultCheck.List({
        @CalculationResultCheck(
                field = "contractFee",
                assetPrice = "assetPrice",
                advancePaymentPercentage = "advancePaymentPercentage",
                message = "Invalid contract fee"
        ),
        @CalculationResultCheck(
                field = "advancePaymentAmount",
                assetPrice = "assetPrice",
                advancePaymentPercentage = "advancePaymentPercentage",
                message = "Invalid advance payment amount"
        )
})
public class ScheduleOfContributions {

    @NotNull
    private String leaseType;

    @NotNull
    private BigDecimal assetPrice;

    @NotNull
    @AdvancePaymentPercentageConstraint
    private float advancePaymentPercentage;

    @NotNull
    private BigDecimal advancePaymentAmount;

    @NotNull
    @LeasePeriodConstraint
    private int leasePeriod;

    @NotNull
    @MarginConstraint
    private float margin;

    @NotNull
    private BigDecimal contractFee;

    @NotNull
    @PaymentDateConstraint
    private int paymentDate;


    public ScheduleOfContributions() {

    }

    public ScheduleOfContributions(String leaseType, BigDecimal assetPrice,
                                   float advancePaymentPercentage, BigDecimal advancePaymentAmount,
                                   int leasePeriod, float margin,
                                   BigDecimal contractFee, int paymentDate) {
        this.leaseType = leaseType;
        this.assetPrice = assetPrice;
        this.advancePaymentPercentage = advancePaymentPercentage;
        this.advancePaymentAmount = advancePaymentAmount;
        this.leasePeriod = leasePeriod;
        this.margin = margin;
        this.contractFee = contractFee;
        this.paymentDate = paymentDate;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public BigDecimal getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(BigDecimal assetPrice) {
        this.assetPrice = assetPrice;
    }

    public float getAdvancePaymentPercentage() {
        return advancePaymentPercentage;
    }

    public void setAdvancePaymentPercentage(float advancePaymentPercentage) {
        this.advancePaymentPercentage = advancePaymentPercentage;
    }

    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    public void setAdvancePaymentAmount(BigDecimal advancePaymentAmount) {
        this.advancePaymentAmount = advancePaymentAmount;
    }

    public int getLeasePeriod() {
        return leasePeriod;
    }

    public void setLeasePeriod(int leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public float getMargin() {
        return margin;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public BigDecimal getContractFee() {
        return contractFee;
    }

    public void setContractFee(BigDecimal contractFee) {
        this.contractFee = contractFee;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(int paymentDate) {
        this.paymentDate = paymentDate;
    }
}
