package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.AddressConstraint;
import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.EmailConstraint;
import lt.swedbank.itacademy.carleasing.validations.constraints.privatecustomer.PhoneNumberConstraint;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Customer {

    @Id
    private ObjectId id;

    @NotNull
    private ObjectId leaseId;

    @NotNull
    @EmailConstraint
    private String email;

    @NotNull
    @PhoneNumberConstraint
    private String phoneNumber;

    @NotNull
    @AddressConstraint
    private String address;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(ObjectId leaseId) {
        this.leaseId = leaseId;
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
}
