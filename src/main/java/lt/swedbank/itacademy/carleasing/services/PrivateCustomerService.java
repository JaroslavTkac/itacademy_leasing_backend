package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateCustomerService {


    @Autowired
    private PrivateCustomerRepository repository;

    public List<PrivateCustomerResponse> getAllPrivateCustomers() {
        return repository.findAll().stream().map(PrivateCustomerResponse::new).collect(Collectors.toList());
    }

    public PrivateCustomerResponse getPrivateCustomerById(String id) {
        PrivateCustomer customer = repository.findPrivateCustomerById(id);

        if (customer != null) {
            return new PrivateCustomerResponse(customer);
        }
        throw new NotFoundException("Sorry, but private customer with id: " + id + " is not present.");
    }

    public PrivateCustomerResponse addNewPrivateCustomer(PrivateCustomer privateCustomer) {
        PrivateCustomer newPrivateCustomer = new PrivateCustomer();

        newPrivateCustomer.setId(privateCustomer.getId());
        newPrivateCustomer.setLeaseId(privateCustomer.getLeaseId());
        newPrivateCustomer.setAddress(privateCustomer.getAddress());
        newPrivateCustomer.setEmail(privateCustomer.getEmail());
        newPrivateCustomer.setFirstName(privateCustomer.getFirstName());
        newPrivateCustomer.setLastName(privateCustomer.getLastName());
        newPrivateCustomer.setPersonalCode(privateCustomer.getPersonalCode());
        newPrivateCustomer.setPhoneNumber(privateCustomer.getPhoneNumber());

        return new PrivateCustomerResponse(repository.save(newPrivateCustomer));
    }


    public void removePrivateCustomer(String id) {
        PrivateCustomer customer = repository.findPrivateCustomerById(id);

        if (customer != null) {
            repository.delete(customer);
        } else {
            throw new NotFoundException("Sorry, but private customer with id: " + id + " is not present.");
        }
    }
}
