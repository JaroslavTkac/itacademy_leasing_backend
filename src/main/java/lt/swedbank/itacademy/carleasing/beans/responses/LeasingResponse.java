package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.Leasing;

import java.math.BigDecimal;
import java.util.List;

public class LeasingResponse extends Response {
    private String id;
    private String assetType;
    private String carBrand;
    private String carModel;
    private String years;
    private int enginePower;
    private BigDecimal assetPrice;
    private float advancePaymentPercentage;
    private BigDecimal advancePaymentAmount;
    private int leasePeriod;
    private float margin;
    private BigDecimal contractFee;
    private int paymentDate;
    private List errorCodes;


    public LeasingResponse(Leasing leasing){
        setId(String.valueOf(leasing.getId()));
        setAssetType(leasing.getAssetType());
        setCarBrand(leasing.getCarBrand());
        setCarModel(leasing.getCarModel());
        setYears(leasing.getYears());
        setEnginePower(leasing.getEnginePower());
        setAssetPrice(leasing.getAssetPrice());
        setAdvancePaymentPercentage(leasing.getAdvancePaymentPercentage());
        setLeasePeriod(leasing.getLeasePeriod());
        setMargin(leasing.getMargin());
        setContractFee(leasing.getContractFee());
        setPaymentDate(leasing.getPaymentDate());
        setAdvancePaymentAmount(leasing.getAdvancePaymentAmount());
        setErrorCodes(leasing.getErrorCodes());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
