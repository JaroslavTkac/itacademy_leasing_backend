package lt.swedbank.itacademy.carleasing.controllers;

import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributions;
import lt.swedbank.itacademy.carleasing.beans.responses.ScheduleOfContributionsResponse;
import lt.swedbank.itacademy.carleasing.services.ScheduleOfContributionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/schedule-of-contributions")
public class ScheduleOfContributionsController {

    @Autowired
    private ScheduleOfContributionsService service;


    //POST
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ScheduleOfContributionsResponse calculateSchedulePayments(@Valid @RequestBody ScheduleOfContributions schedule) {
        return service.calculateScheduleOfContributions(schedule);
    }
}
