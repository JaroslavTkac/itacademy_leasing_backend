package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.Customer;

public class CustomerResponse extends Response {
    private Object id;
    private Object leaseId;
    private String email;
    private String phoneNumber;
    private String address;

    public CustomerResponse(){

    }

    public CustomerResponse(Customer customer) {
        setId(String.valueOf(customer.getId()));
        setLeaseId(String.valueOf(customer.getLeaseId()));
        setEmail(customer.getEmail());
        setPhoneNumber(customer.getPhoneNumber());
        setAddress(customer.getAddress());
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Object leaseId) {
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
