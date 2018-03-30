package lt.swedbank.itacademy.carleasing.controllers;


import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.services.CorporateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/corporate_customer")
public class CorporateCustomerController {

    @Autowired
    private CorporateCustomerService corporateCustomerService;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CorporateCustomerResponse> getAllCorporateCustomers() {
        return corporateCustomerService.getAllCorporateCustomers();
    }

    //GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CorporateCustomerResponse getCorporateCustomerById(@PathVariable("id") String id) {
        return corporateCustomerService.getCorporateCustomerById(id);
    }

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CorporateCustomerResponse add(@Valid @RequestBody CorporateCustomer corporateCustomer) {
        return corporateCustomerService.addNewCorporateCustomer(corporateCustomer);
    }

    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removeCorporateCustomer(@PathVariable("id") String id) {
        corporateCustomerService.deleteCorporateCustomer(id);
    }
}
