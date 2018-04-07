package lt.swedbank.itacademy.carleasing.controllers;


import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;
import lt.swedbank.itacademy.carleasing.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Car> getAllCars() {
        return carsService.getAllCars();
    }


    //GET
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public List<CarBrandResponse> getAllBrands() {
        return carsService.getAllBrands();
    }


    //GET
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<CarModelResponse> getAllModelsByBrand(@PathVariable("name") String brandName) {
        return carsService.getAllModelsByBrand(brandName);
    }


    //POST
//    @RequestMapping(value = "/initcars", method = RequestMethod.POST)
//    public String initMongoDataBaseWithDefaultCars() {
//        //1 Wiping all current data
//        carsService.wipeAllData();
//        //2 Pushing data to db
//        carsService.initializeCars();
//
//        return "{\n\"status\": \"OK\"\n}";
//    }
}
