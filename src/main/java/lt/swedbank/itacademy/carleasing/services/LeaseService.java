package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CustomerLease;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.IllegalParameterException;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import lt.swedbank.itacademy.carleasing.repositories.LeaseRepository;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        newLease.setStatus("Waiting");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        newLease.setApplicationDate(format.format(cal.getTime()));

        return new LeaseResponse(repository.save(newLease));
    }


    public void deleteLease(String leaseId) {
        repository.delete(repository.findLeasingById(leaseId));
    }

    public LeaseResponse updateStatus(String leaseId, String newStatus) {
        Lease leaseToUpdate = repository.findLeasingById(leaseId);

        if (leaseToUpdate == null) {
            throw new NotFoundException("Sorry, but lease with id: " + leaseId + " is not present.");
        }

        if (newStatus.toLowerCase().equals("accepted") || newStatus.toLowerCase().equals("rejected")) {
            leaseToUpdate.setStatus(newStatus);
            return new LeaseResponse(repository.save(leaseToUpdate));
        } else {
            throw new IllegalParameterException("Illegal status parameter found.");
        }
    }

    public CustomerLease getLeaseWithCustomer(String leaseId) {
        CustomerLease customerLease;
        Lease lease = repository.findLeasingById(leaseId);
       
        List<PrivateCustomerResponse> privateCustomers = privateCustomerRepository.findAll().stream()
                .map(PrivateCustomerResponse::new)
                .collect(Collectors.toList());

        for (PrivateCustomerResponse currentPrivateCustomer : privateCustomers) {
            if (currentPrivateCustomer.getLeaseId().equals(String.valueOf(lease.getId()))) {
                customerLease = new CustomerLease(currentPrivateCustomer, new LeaseResponse(lease));
                return customerLease;
            }
        }

        List<CorporateCustomerResponse> corporateCustomer = corporateCustomerRepository.findAll().stream()
                .map(CorporateCustomerResponse::new)
                .collect(Collectors.toList());

        for (CorporateCustomerResponse currentCorporateCustomer : corporateCustomer) {
            if (currentCorporateCustomer.getLeaseId().equals(String.valueOf(lease.getId()))) {
                customerLease = new CustomerLease(currentCorporateCustomer, new LeaseResponse(lease));
                return customerLease;
            }
        }

        throw new NotFoundException("Sorry, but lease with id: " + leaseId + " is not present.");
    }
}
