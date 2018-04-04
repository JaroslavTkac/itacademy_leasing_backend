package lt.swedbank.itacademy.carleasing.services;


import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributions;
import lt.swedbank.itacademy.carleasing.beans.responses.ScheduleOfContributionsResponse;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ScheduleOfContributionsService {


    public ScheduleOfContributionsResponse calculateScheduleOfContributions(@Valid ScheduleOfContributions schedule) {
        return null;
    }

}
