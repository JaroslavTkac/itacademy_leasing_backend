package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.NameConstraint;
import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.PersonalCodeConstraint;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "private_customers")
public class PrivateCustomer extends Customer{


    @NotNull
    @NameConstraint
    private String firstName;

    @NotNull
    @NameConstraint
    private String lastName;

    @NotNull
    @PersonalCodeConstraint
    private String personalCode;


    public PrivateCustomer(){

    }

    public PrivateCustomer(ObjectId id, ObjectId leaseId, String firstName, String lastName, String personalCode, String email, String phoneNumber,
                           String address){
        setId(id);
        setLeaseId(leaseId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setPersonalCode(personalCode);
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

}
