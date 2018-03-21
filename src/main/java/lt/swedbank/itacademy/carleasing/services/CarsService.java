package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.repositories.CarBrandRepository;
import lt.swedbank.itacademy.carleasing.repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarsService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;



}
