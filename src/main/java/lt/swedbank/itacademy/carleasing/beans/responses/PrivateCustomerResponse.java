package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;

import java.util.List;

public class PrivateCustomerResponse extends Response {
    private Object id;
    private String firstName;
    private String lastName;
    private String personalCode;
    private String email;
    private String phoneNumber;
    private String address;
    private List errorCodes;


    public PrivateCustomerResponse(PrivateCustomer privateCustomer){
        setId(String.valueOf(privateCustomer.getId()));
        setFirstName(privateCustomer.getFirstName());
        setLastName(privateCustomer.getLastName());
        setEmail(privateCustomer.getEmail());
        setPhoneNumber(privateCustomer.getPhoneNumber());
        setAddress(privateCustomer.getAddress());
        setPersonalCode(privateCustomer.getPersonalCode());
        setErrorCodes(privateCustomer.getErrorCodes());
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
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

    public List getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List errorCodes) {
        this.errorCodes = errorCodes;
    }
}
