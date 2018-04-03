package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompleteLeaseService {

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private CorporateCustomerService corporateCustomerService;

    @Autowired
    private PrivateCustomerService privateCustomerService;

    public CompleteLeaseResponse addNewCompleteLease(CompleteLease lease) {
        ObjectId leaseId = new ObjectId();

        lease.getLease().setId(leaseId);
        leaseService.addNewLease(lease.getLease());

        if(lease.getLease().getLeaseType().equals("Private")){
            lease.getPrivateCustomer().setLeaseId(leaseId);
            lease.getPrivateCustomer().setId(new ObjectId());
            privateCustomerService.addNewPrivateCustomer(lease.getPrivateCustomer());
        }
        if(lease.getLease().getLeaseType().equals("Corporate")){
            lease.getCorporateCustomer().setLeaseId(leaseId);
            lease.getCorporateCustomer().setId(new ObjectId());
            corporateCustomerService.addNewCorporateCustomer(lease.getCorporateCustomer());
        }

        return new CompleteLeaseResponse(lease);
    }
}
