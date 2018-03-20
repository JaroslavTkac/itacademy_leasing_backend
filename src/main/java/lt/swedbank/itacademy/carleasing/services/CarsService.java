package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.repositories.CarBrandsRepository;
import lt.swedbank.itacademy.carleasing.repositories.CarModelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarsService {

    @Autowired
    private CarBrandsRepository carBrandsRepository;

    @Autowired
    private CarModelsRepository carModelsRepository;



}
