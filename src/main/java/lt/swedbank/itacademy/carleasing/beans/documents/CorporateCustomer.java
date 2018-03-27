package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.validations.constraints.corporatecustomer.CompanyCodeConstraint;
import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.NameConstraint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "corporate_customers")
public class CorporateCustomer extends Customer{


    @NotNull
    @NameConstraint
    private String companyName;

    @NotNull
    @CompanyCodeConstraint
    private String companyCode;


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
