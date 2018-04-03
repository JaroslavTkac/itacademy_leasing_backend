package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer.CompanyCodeConstraint;
import lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer.CompanyNameConstraint;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "corporate_customers")
public class CorporateCustomer extends Customer{


    @NotNull
    @CompanyNameConstraint
    private String companyName;

    @NotNull
    @CompanyCodeConstraint
    private String companyCode;

    public CorporateCustomer(){

    }

    public CorporateCustomer(ObjectId id, ObjectId leaseId, String email, String phoneNumber, String address, String companyName, String companyCode) {
        setId(id);
        setLeaseId(leaseId);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
        setCompanyCode(companyCode);
        setCompanyName(companyName);
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

}
