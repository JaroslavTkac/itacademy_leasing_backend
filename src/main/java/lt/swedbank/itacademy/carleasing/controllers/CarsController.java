package lt.swedbank.itacademy.carleasing.controllers;


<<<<<<< HEAD
import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;
=======
import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;
import lt.swedbank.itacademy.carleasing.beans.documents.Car;
>>>>>>> backend-skeleton-addons
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
<<<<<<< HEAD
    public List<Car> getAllCars() {
=======
    public List<Car> getAllCars(){
>>>>>>> backend-skeleton-addons
        // carService.getModels
        // carService.getBrands
        return carsService.getAllCars();
    }


    //GET
<<<<<<< HEAD
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public List<CarBrandResponse> getAllBrands() {
        return carsService.getAllBrands();
=======
    @RequestMapping(value = "/model/id", method = RequestMethod.GET)
    public CarModel getCarModelById(){
        return null;
>>>>>>> backend-skeleton-addons
    }


    //GET
<<<<<<< HEAD
    @RequestMapping(value = "/brand/{name}", method = RequestMethod.GET)
    public List<CarModelResponse> getAllModelsByBrand(@PathVariable("name") String brandName) {
        return carsService.getAllModelsByBrand(brandName);
=======
    @RequestMapping(value = "/brand/id", method = RequestMethod.GET)
    public CarModel getCarBrandById(){
        return null;
>>>>>>> backend-skeleton-addons
    }


    //POST
    @RequestMapping(value = "/initcars", method = RequestMethod.POST)
    public String initMongoDataBaseWithDefaultCars() {
        //1 Wiping all current data
        carsService.wipeAllData();
        //2 Pushing data to db
        carsService.initializeCars();

        return "{\n\"status\": \"OK\"\n}";
    }
}
