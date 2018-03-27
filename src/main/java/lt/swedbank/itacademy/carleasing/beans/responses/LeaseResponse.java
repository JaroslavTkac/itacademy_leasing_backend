package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;

import java.math.BigDecimal;

public class LeaseResponse extends Response {
    private String id;
    private String assetType;
    private String leaseType;
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
    private String status;


    public LeaseResponse(Lease lease){
        setId(String.valueOf(lease.getId()));
        setLeaseType(String.valueOf(lease.getLeaseType()));
        setAssetType(lease.getAssetType());
        setCarBrand(lease.getCarBrand());
        setCarModel(lease.getCarModel());
        setYears(lease.getYears());
        setEnginePower(lease.getEnginePower());
        setAssetPrice(lease.getAssetPrice());
        setAdvancePaymentPercentage(lease.getAdvancePaymentPercentage());
        setLeasePeriod(lease.getLeasePeriod());
        setMargin(lease.getMargin());
        setContractFee(lease.getContractFee());
        setPaymentDate(lease.getPaymentDate());
        setAdvancePaymentAmount(lease.getAdvancePaymentAmount());
        setStatus(lease.getStatus());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
