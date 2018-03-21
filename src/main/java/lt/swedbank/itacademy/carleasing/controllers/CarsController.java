package lt.swedbank.itacademy.carleasing.controllers;


import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;
import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cars")
public class CarsController {

    @Autowired
    private CarsService carsService;


    //get visas masinas su joms priklausanciais modeliais
    // BMW: [320, 525, 735] etc.

    //GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Car> getAllCars(){
        // carService.getModels
        // carService.getBrands
        return null;
        //return carsService.getAllPrivateCustomers();
    }

    //GET
    @RequestMapping(value = "/model/id", method = RequestMethod.GET)
    public CarModel getCarModelById(){
        return null;
    }

    //GET
    @RequestMapping(value = "/brand/id", method = RequestMethod.GET)
    public CarModel getCarBrandById(){
        return null;
    }


    //POST
    @RequestMapping(value = "/postall", method = RequestMethod.POST)
    public String initMongoDataBaseWithDefaultCars(){
        //1 Wiping all current data

        //2 Pushing data to db


        return "OK";
    }
}
