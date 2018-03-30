package lt.swedbank.itacademy.carleasing.controllers;

import lt.swedbank.itacademy.carleasing.beans.documents.CustomerLease;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/leasing")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LeaseResponse> getAllLeases() {
        return leaseService.getAllLeases();
    }

    //GET
    @RequestMapping(value = "/get_detailed_leases", method = RequestMethod.GET)
    public List<CustomerLease> getAllLeasesWithCustomers() {
        return leaseService.getAllLeasesWithCustomers();
    }

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LeaseResponse addNewLease(@Valid @RequestBody Lease lease) {
        return leaseService.addNewLease(lease);
    }

    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removeLease(@PathVariable("id") String id) {
        leaseService.deleteLease(id);
    }


}