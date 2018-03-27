package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.beans.responses.CustomerResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;

public class CustomerLease {
    private CustomerResponse customer;
    private LeaseResponse lease;

    public CustomerLease(CustomerResponse customer, LeaseResponse lease) {
        setLease(lease);
        setCustomer(customer);
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public LeaseResponse getLease() {
        return lease;
    }

    public void setLease(LeaseResponse lease) {
        this.lease = lease;
    }
}

