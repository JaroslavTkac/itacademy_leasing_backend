package lt.swedbank.itacademy.carleasing.controllers;


import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.services.PrivateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/private-customer")
public class PrivateCustomerController {


    @Autowired
    private PrivateCustomerService privateCustomerService;

    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PrivateCustomerResponse> getAllPrivateCustomers() {
        return privateCustomerService.getAllPrivateCustomers();
    }

    //GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PrivateCustomerResponse getPrivateCustomerById(@PathVariable("id") String id) {
        return privateCustomerService.getPrivateCustomerById(id);
    }

    //ADD
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public PrivateCustomerResponse addNewPrivateCustomer(@Valid @RequestBody PrivateCustomer privateCustomer) {
//        return privateCustomerService.addNewPrivateCustomer(privateCustomer);
//    }

    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removePrivateCustomer(@PathVariable("id") String id) {
        privateCustomerService.removePrivateCustomer(id);
    }

}
