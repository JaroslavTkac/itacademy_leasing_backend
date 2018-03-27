package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import org.bson.types.ObjectId;
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
        List<PrivateCustomerResponse> privateCustomers = repository.findAll().stream().map(PrivateCustomerResponse::new).collect(Collectors.toList());
        for (PrivateCustomerResponse currentPrivateCustomer : privateCustomers) {
            if (currentPrivateCustomer.getId().equals(id)) {
                return currentPrivateCustomer;
            }
        }
        return null;
    }

    public PrivateCustomer addNewPrivateCustomer(PrivateCustomer privateCustomer) {
        PrivateCustomer newPrivateCustomer = new PrivateCustomer();

        newPrivateCustomer.setId(new ObjectId());
        newPrivateCustomer.setLeaseId(privateCustomer.getLeaseId());
        newPrivateCustomer.setAddress(privateCustomer.getAddress());
        newPrivateCustomer.setEmail(privateCustomer.getEmail());
        newPrivateCustomer.setFirstName(privateCustomer.getFirstName());
        newPrivateCustomer.setLastName(privateCustomer.getLastName());
        newPrivateCustomer.setPersonalCode(privateCustomer.getPersonalCode());
        newPrivateCustomer.setPhoneNumber(privateCustomer.getPhoneNumber());

        return repository.save(newPrivateCustomer);
    }


}
