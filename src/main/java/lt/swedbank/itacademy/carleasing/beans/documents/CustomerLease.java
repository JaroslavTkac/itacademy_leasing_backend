package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;

public class CustomerLease {
    private PrivateCustomerResponse privateCustomer;
    private LeaseResponse lease;

    public CustomerLease(PrivateCustomerResponse privateCustomer, LeaseResponse lease) {
        setLease(lease);
        setPrivateCustomer(privateCustomer);
    }





    public PrivateCustomerResponse getPrivateCustomer() {
        return privateCustomer;
    }

    public void setPrivateCustomer(PrivateCustomerResponse privateCustomer) {
        this.privateCustomer = privateCustomer;
    }

    public LeaseResponse getLease() {
        return lease;
    }

    public void setLease(LeaseResponse lease) {
        this.lease = lease;
    }
}

