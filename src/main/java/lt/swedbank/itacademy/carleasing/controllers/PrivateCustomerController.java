package lt.swedbank.itacademy.carleasing.controllers;


import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.services.PrivateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/private_customer")
public class PrivateCustomerController {

    @Autowired
    private PrivateCustomerService privateCustomerService;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PrivateCustomerResponse> getAllPrivateCustomers(){
        return privateCustomerService.getAllPrivateCustomers();
    }

    //GET
    //get customer by id


    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PrivateCustomerResponse addNewPrivateCustomer(@Valid @RequestBody PrivateCustomer privateCustomer){
        return new PrivateCustomerResponse(privateCustomerService.addNewPrivateCustomer(privateCustomer));
    }
}
