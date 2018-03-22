package lt.swedbank.itacademy.carleasing.beans.documents;


import lt.swedbank.itacademy.carleasing.validations.constraints.lease.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "leases")
public class Lease {

    @Id
    private ObjectId id;

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
    @IntegerMismatchConstraint
    @EnginePowerConstraint
    private int enginePower;

    @NotNull
    //@FloatMismatchConstraint
    @AssetPriceConstraint
    private BigDecimal assetPrice;

    @NotNull
    //@FloatMismatchConstraint
    @AdvancePaymentPercentageConstraint
    private float advancePaymentPercentage;

    @NotNull
    //@FloatMismatchConstraint
    private BigDecimal advancePaymentAmount;

    @NotNull
    //@LeasePeriodConstraint
    private int leasePeriod;

    @NotNull
    //@FloatMismatchConstraint
    @MarginConstraint
    private float margin;

    @NotNull
    //@FloatMismatchConstraint
    private BigDecimal contractFee;

    @NotNull
    //@IntegerMismatchConstraint
    @PaymentDateConstraint
    private int paymentDate;


    private List errorCodes;

    public Lease(){

    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public List getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List errorCodes) {
        this.errorCodes = errorCodes;
    }
}
