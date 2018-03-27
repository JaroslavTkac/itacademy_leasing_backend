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
public class LeasingController {


    @Autowired
    private LeaseService leaseService;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LeaseResponse> getAllLeasings(){
       return leaseService.getAllLeasings();
    }


    //GET
    //lease and customer who assigned to that lease
    @RequestMapping(value = "/get_detailed_leases", method = RequestMethod.GET)
    public List<CustomerLease> getAllLeasesWithCustomers(){
        return leaseService.getAllLeasesWithCustomers();
    }

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LeaseResponse addLeasing(@Valid @RequestBody Lease lease){
        return new LeaseResponse(leaseService.addNewLeasing(lease));
    }



}