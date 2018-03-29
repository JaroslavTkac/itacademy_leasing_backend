package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;


public class CorporateCustomerResponse extends CustomerResponse {
    private String id;
    private String leaseId;
    private String companyName;
    private String companyCode;
    private String email;
    private String phoneNumber;
    private String address;

    public CorporateCustomerResponse(CorporateCustomer customer) {
        setId(String.valueOf(customer.getId()));
        setLeaseId(String.valueOf(customer.getLeaseId()));
        setCompanyName(customer.getCompanyName());
        setCompanyCode(customer.getCompanyCode());
        setAddress(customer.getAddress());
        setEmail(customer.getEmail());
        setPhoneNumber(customer.getPhoneNumber());
    }

    public CorporateCustomerResponse(String id, String leaseId, String companyName, String companyCode, String email, String phoneNumber, String address) {
        this.id = id;
        this.leaseId = leaseId;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }
}
