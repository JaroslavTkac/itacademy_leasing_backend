package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;

import java.math.BigDecimal;

public class CompleteLeaseResponse {
    //lease
    private String leaseId;
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

    //customer
    private String customerId;
    private String customerLeaseId;
    private String email;
    private String phoneNumber;
    private String address;

    //private
    private String firstName;
    private String lastName;
    private String personalCode;

    //corporate
    private String companyCode;
    private String companyName;


    public CompleteLeaseResponse(CompleteLease completeLease){
        setLeaseId(String.valueOf(completeLease.getLease().getId()));
        setLeaseType(String.valueOf(completeLease.getLease().getLeaseType()));
        setAssetType(completeLease.getLease().getAssetType());
        setCarBrand(completeLease.getLease().getCarBrand());
        setCarModel(completeLease.getLease().getCarModel());
        setYears(completeLease.getLease().getYears());
        setEnginePower(completeLease.getLease().getEnginePower());
        setAssetPrice(completeLease.getLease().getAssetPrice());
        setAdvancePaymentPercentage(completeLease.getLease().getAdvancePaymentPercentage());
        setLeasePeriod(completeLease.getLease().getLeasePeriod());
        setMargin(completeLease.getLease().getMargin());
        setContractFee(completeLease.getLease().getContractFee());
        setPaymentDate(completeLease.getLease().getPaymentDate());
        setAdvancePaymentAmount(completeLease.getLease().getAdvancePaymentAmount());
        setStatus(completeLease.getLease().getStatus());

        if(completeLease.getPrivateCustomer() != null){
            setCustomerId(String.valueOf(completeLease.getPrivateCustomer().getId()));
            setCustomerLeaseId(String.valueOf(completeLease.getPrivateCustomer().getLeaseId()));
            setEmail(completeLease.getPrivateCustomer().getEmail());
            setPhoneNumber(completeLease.getPrivateCustomer().getPhoneNumber());
            setAddress(completeLease.getPrivateCustomer().getAddress());
            setFirstName(completeLease.getPrivateCustomer().getFirstName());
            setLastName(completeLease.getPrivateCustomer().getLastName());
            setPersonalCode(completeLease.getPrivateCustomer().getPersonalCode());
        }
        if(completeLease.getCorporateCustomer() != null){
            setCustomerId(String.valueOf(completeLease.getCorporateCustomer().getId()));
            setCustomerLeaseId(String.valueOf(completeLease.getCorporateCustomer().getLeaseId()));
            setEmail(completeLease.getCorporateCustomer().getEmail());
            setPhoneNumber(completeLease.getCorporateCustomer().getPhoneNumber());
            setAddress(completeLease.getCorporateCustomer().getAddress());
            setCompanyCode(completeLease.getCorporateCustomer().getCompanyCode());
            setCompanyName(completeLease.getCorporateCustomer().getCompanyName());
        }
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerLeaseId() {
        return customerLeaseId;
    }

    public void setCustomerLeaseId(String customerLeaseId) {
        this.customerLeaseId = customerLeaseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
