package lt.swedbank.itacademy.carleasing.controllers;

import lt.swedbank.itacademy.carleasing.beans.documents.CustomerLease;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lease")
public class LeaseController {

    @Autowired
    private LeaseService service;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LeaseResponse> getAllLeases() {
        return service.getAllLeases();
    }

    //GET
    @RequestMapping(value = "/detailed-leases", method = RequestMethod.GET)
    public List<CustomerLease> getAllLeasesWithCustomers() {
        return service.getAllLeasesWithCustomers();
    }

    //GET
    @RequestMapping(value = "/{id}/with-customer", method = RequestMethod.GET)
    public CustomerLease getLeaseWithCustomer(@PathVariable("id") String leaseId){
        return service.getLeaseWithCustomer(leaseId);
    }

    //PUT
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    public LeaseResponse updateStatus(@PathVariable("id") String leaseId, @PathVariable("status") String newStatus) {
        return service.updateStatus(leaseId, newStatus);
    }

    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removeLease(@PathVariable("id") String leaseId) {
        service.deleteLease(leaseId);
    }


}