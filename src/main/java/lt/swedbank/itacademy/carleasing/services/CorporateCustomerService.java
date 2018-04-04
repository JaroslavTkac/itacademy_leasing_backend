package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CorporateCustomerService {


    @Autowired
    private CorporateCustomerRepository repository;

    public List<CorporateCustomerResponse> getAllCorporateCustomers() {
        return repository.findAll().stream().map(CorporateCustomerResponse::new).collect(Collectors.toList());
    }

    public CorporateCustomerResponse addNewCorporateCustomer(@Valid CorporateCustomer corporateCustomer) {
        CorporateCustomer newCorporateCustomer = new CorporateCustomer();

        newCorporateCustomer.setId(corporateCustomer.getId());
        newCorporateCustomer.setLeaseId(corporateCustomer.getLeaseId());
        newCorporateCustomer.setAddress(corporateCustomer.getAddress());
        newCorporateCustomer.setEmail(corporateCustomer.getEmail());
        newCorporateCustomer.setCompanyCode(corporateCustomer.getCompanyCode());
        newCorporateCustomer.setCompanyName(corporateCustomer.getCompanyName());
        newCorporateCustomer.setPhoneNumber(corporateCustomer.getPhoneNumber());

        return new CorporateCustomerResponse(repository.save(newCorporateCustomer));
    }

    public CorporateCustomerResponse getCorporateCustomerById(String id) {
        CorporateCustomer customer = repository.findCorporateCustomersById(id);

        if (customer != null) {
            return new CorporateCustomerResponse(customer);
        }
        throw new NotFoundException("Sorry, but corporate customer with id: " + id + " is not present.");
    }

    public void deleteCorporateCustomer(String id) {
        CorporateCustomer customer = repository.findCorporateCustomersById(id);

        if (customer != null) {
            repository.delete(customer);
        } else {
            throw new NotFoundException("Sorry, but corporate customer with id: " + id + " is not present.");
        }

    }
}
