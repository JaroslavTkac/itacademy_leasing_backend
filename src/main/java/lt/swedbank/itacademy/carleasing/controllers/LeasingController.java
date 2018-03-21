package lt.swedbank.itacademy.carleasing.controllers;

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

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LeaseResponse addLeasing(@Valid @RequestBody Lease lease){
        return new LeaseResponse(leaseService.addNewLeasing(lease));
    }



}