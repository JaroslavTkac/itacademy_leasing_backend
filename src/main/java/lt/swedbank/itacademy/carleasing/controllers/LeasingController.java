package lt.swedbank.itacademy.carleasing.controllers;

import lt.swedbank.itacademy.carleasing.beans.documents.Leasing;
import lt.swedbank.itacademy.carleasing.beans.responses.LeasingResponse;
import lt.swedbank.itacademy.carleasing.services.LeasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/leasing")
public class LeasingController {


    @Autowired
    private LeasingService leasingService;


    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LeasingResponse> getAllLeasings(){
       return leasingService.getAllLeasings();
    }

    //ADD
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LeasingResponse addLeasing(@Valid @RequestBody Leasing leasing){
        return new LeasingResponse(leasingService.addNewLeasing(leasing));
    }


}