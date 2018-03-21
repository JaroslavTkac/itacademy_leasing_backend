package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import lt.swedbank.itacademy.carleasing.validations.PrivateCustomerValidations;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateCustomerService extends lt.swedbank.itacademy.carleasing.services.Service{

    @Autowired
    private PrivateCustomerRepository repository;

    private PrivateCustomerValidations validations;


    public List<PrivateCustomerResponse> getAllPrivateCustomers() {
        return repository.findAll().stream().map(PrivateCustomerResponse::new).collect(Collectors.toList());
    }


    public PrivateCustomer addNewPrivateCustomer(PrivateCustomer privateCustomer) {
        validations = new PrivateCustomerValidations();
        errorCodes = new ArrayList<>();


        //TODO validatorius
        //errorCodes.add(validations.validateAssetType(lease.getAssetType()));

        //Checking is there any errors
        List<Integer> actualErrors = validations.checkForActualError(errorCodes);

        if(actualErrors.size() == 0) {
            //if no errors
            //saving to DB
            return repository.save(initNewPrivateCustomer(privateCustomer, actualErrors));
        }
        else{
            //if have error, returning empty object with error codes
            return initNewPrivateCustomer(privateCustomer, actualErrors);
        }
    }


    private PrivateCustomer initNewPrivateCustomer(PrivateCustomer privateCustomer, List<Integer> errorCodes){
        PrivateCustomer newPrivateCustomer = new PrivateCustomer();

        newPrivateCustomer.setErrorCodes(errorCodes);
        newPrivateCustomer.setId(new ObjectId());
        if(newPrivateCustomer.getErrorCodes().size() == 0) {
            newPrivateCustomer.setAddress(privateCustomer.getAddress());
            newPrivateCustomer.setEmail(privateCustomer.getEmail());
            newPrivateCustomer.setFirstName(privateCustomer.getFirstName());
            newPrivateCustomer.setLastName(privateCustomer.getLastName());
            newPrivateCustomer.setPersonalCode(privateCustomer.getPersonalCode());
            newPrivateCustomer.setPhoneNumber(privateCustomer.getPhoneNumber());
        }
        else{
            newPrivateCustomer.setAddress("");
            newPrivateCustomer.setEmail("");
            newPrivateCustomer.setFirstName("");
            newPrivateCustomer.setLastName("");
            newPrivateCustomer.setPersonalCode("");
            newPrivateCustomer.setPhoneNumber("");

        }
        return newPrivateCustomer;
    }

}
