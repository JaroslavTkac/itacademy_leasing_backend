package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CustomerLease;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import lt.swedbank.itacademy.carleasing.repositories.LeaseRepository;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository repository;

    @Autowired
    private PrivateCustomerRepository privateCustomerRepository;

    @Autowired
    private CorporateCustomerRepository corporateCustomerRepository;

    public List<LeaseResponse> getAllLeases() {
        return repository.findAll().stream().map(LeaseResponse::new).collect(Collectors.toList());
    }

    public List<CustomerLease> getAllLeasesWithCustomers() {
        List<LeaseResponse> leases = repository.findAll().stream().map(LeaseResponse::new).collect(Collectors.toList());
        List<PrivateCustomerResponse> privateCustomers = privateCustomerRepository.findAll().stream().map(PrivateCustomerResponse::new).collect(Collectors.toList());
        List<CorporateCustomerResponse> corporateCustomer = corporateCustomerRepository.findAll().stream().map(CorporateCustomerResponse::new).collect(Collectors.toList());
        List<CustomerLease> customerLeases = new ArrayList<>();
        CustomerLease customer;

        for (LeaseResponse currentLease : leases) {
            for (PrivateCustomerResponse currentResponse : privateCustomers) {
                if (currentLease.getId().equals(currentResponse.getLeaseId())) {
                    customer = new CustomerLease(currentResponse, currentLease);
                    customerLeases.add(customer);
                }
            }
            for (CorporateCustomerResponse currentResponse : corporateCustomer) {
                if (currentLease.getId().equals(currentResponse.getLeaseId())) {
                    customer = new CustomerLease(currentResponse, currentLease);
                    customerLeases.add(customer);
                }
            }
        }
        return customerLeases;
    }

    public LeaseResponse addNewLease(@Valid Lease lease) {
        Lease newLease = new Lease();
        //newLease.setCustomerId(new ObjectId());
        newLease.setId(lease.getId());
        newLease.setAssetType(lease.getAssetType());
        newLease.setCarBrand(lease.getCarBrand());
        newLease.setCarModel(lease.getCarModel());
        newLease.setYears(lease.getYears());
        newLease.setEnginePower(lease.getEnginePower());
        newLease.setAssetPrice(lease.getAssetPrice());
        newLease.setAdvancePaymentPercentage(lease.getAdvancePaymentPercentage());
        newLease.setAdvancePaymentAmount(lease.getAdvancePaymentAmount());
        newLease.setLeasePeriod(lease.getLeasePeriod());
        newLease.setMargin(lease.getMargin());
        newLease.setContractFee(lease.getContractFee());
        newLease.setPaymentDate(lease.getPaymentDate());

        return new LeaseResponse(repository.save(newLease));
    }


    public void deleteLease(String id) {
        repository.delete(repository.findLeasingById(id));
    }
}
