package lt.swedbank.itacademy.carleasing.beans.documents;


import lt.swedbank.itacademy.carleasing.validations.constraints.lease.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
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
@Document(collection = "leases")
public class Lease {

    @Id
    private ObjectId id;

    @NotNull
    private String leaseType;

    @NotNull
    private String assetType;

    @NotNull
    @NotEmpty
    private String carBrand;

    @NotNull
    @NotEmpty
    private String carModel;

    @NotNull
    @YearsConstraint
    private String years;

    @NotNull
    @EnginePowerConstraint
    private int enginePower;

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

    private String status;  //TODO validation in Sprint 3


    public Lease() {

    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
