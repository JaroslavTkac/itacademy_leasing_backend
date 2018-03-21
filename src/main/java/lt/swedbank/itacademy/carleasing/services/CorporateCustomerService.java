package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CorporateCustomerService {

    @Autowired
    private CorporateCustomerRepository corporateCustomerRepository;

}
