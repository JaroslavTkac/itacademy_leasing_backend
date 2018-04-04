package lt.swedbank.itacademy.carleasing.controllers;

import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import lt.swedbank.itacademy.carleasing.services.CompleteLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/complete-lease")
public class CompleteLeaseController {

    @Autowired
    private CompleteLeaseService service;

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CompleteLeaseResponse addNewLease(@Valid @RequestBody CompleteLease lease) {
        return service.addNewCompleteLease(lease);
    }

}
