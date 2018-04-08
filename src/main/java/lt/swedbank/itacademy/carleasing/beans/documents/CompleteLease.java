package lt.swedbank.itacademy.carleasing.beans.documents;

import javax.validation.Valid;

public class CompleteLease {

    @Valid
    private Lease lease;
    @Valid
    private PrivateCustomer privateCustomer;
    @Valid
    private CorporateCustomer corporateCustomer;

    public CompleteLease() {

    }

    public CompleteLease(@Valid Lease lease, @Valid PrivateCustomer privateCustomer) {
        setLease(lease);
        setPrivateCustomer(privateCustomer);
    }

    public CompleteLease(@Valid Lease lease, @Valid CorporateCustomer corporateCustomer) {
        setLease(lease);
        setCorporateCustomer(corporateCustomer);
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public PrivateCustomer getPrivateCustomer() {
        return privateCustomer;
    }

    public void setPrivateCustomer(PrivateCustomer privateCustomer) {
        this.privateCustomer = privateCustomer;
    }

    public CorporateCustomer getCorporateCustomer() {
        return corporateCustomer;
    }

    public void setCorporateCustomer(CorporateCustomer corporateCustomer) {
        this.corporateCustomer = corporateCustomer;
    }
}
