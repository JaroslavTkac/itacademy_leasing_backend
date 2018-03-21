package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CarBrand;
import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;
import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;
import lt.swedbank.itacademy.carleasing.repositories.CarBrandRepository;
import lt.swedbank.itacademy.carleasing.repositories.CarModelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;


    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();
        List<CarModelResponse> allCurrentBrandModels;
        List<CarBrandResponse> allCarBrands = carBrandRepository.findAll().stream().map(CarBrandResponse::new).collect(Collectors.toList());
        List<CarModelResponse> allCarModels = carModelRepository.findAll().stream().map(CarModelResponse::new).collect(Collectors.toList());

        for (CarBrandResponse brand : allCarBrands) {
            allCurrentBrandModels = new ArrayList<>();
            for (CarModelResponse model : allCarModels) {
                if (brand.getId().equals(model.getBrandId())) {
                    allCurrentBrandModels.add(model);
                }
            }
            allCars.add(new Car(brand, allCurrentBrandModels));
        }

        return allCars;
    }


    public List<CarBrandResponse> getAllBrands() {
        return carBrandRepository.findAll().stream().map(CarBrandResponse::new).collect(Collectors.toList());
    }


    public List<CarModelResponse> getAllModelsByBrand(String brandName) {
        CarBrand carBrand = carBrandRepository.findCarBrandsByBrand(brandName);
        return carModelRepository.findCarModelsByBrandId(carBrand.getId()).stream().map(CarModelResponse::new).collect(Collectors.toList());
    }


    public void wipeAllData() {
        //wiping data
        carBrandRepository.deleteAll();
        carModelRepository.deleteAll();
    }


    public void initializeCars() {
        ObjectId brandId;

        //Alfa Romeo
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Alfa Romeo"));

        carModelRepository.save(new CarModel(new ObjectId(), "147", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "155", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Giulia", brandId));

        //Audi
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Audi"));

        carModelRepository.save(new CarModel(new ObjectId(), "A2", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "A3", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "A4", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "A8", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "R8", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "TT", brandId));

        //BMW
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "BMW"));

        carModelRepository.save(new CarModel(new ObjectId(), "3", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "5", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "6", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "X3", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "X5", brandId));

        //Ford
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Ford"));

        carModelRepository.save(new CarModel(new ObjectId(), "Focus", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Mustang", brandId));

        //Honda
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Honda"));

        carModelRepository.save(new CarModel(new ObjectId(), "Accord", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Civic", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CR-V", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CH-R", brandId));

        //Jaguar
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Jaguar"));

        carModelRepository.save(new CarModel(new ObjectId(), "S-Type", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "XFR", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "XJ13", brandId));

        //Lamborghini
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Lamborghini"));

        carModelRepository.save(new CarModel(new ObjectId(), "Countach", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Gallardo", brandId));

        //Lexus
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Lexus"));

        carModelRepository.save(new CarModel(new ObjectId(), "GS", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "LS", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "IS", brandId));

        //Mazda
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Mazda"));

        carModelRepository.save(new CarModel(new ObjectId(), "RX-7", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "6", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "3", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CX-5", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "MX-5", brandId));

        //Mercedes-benz
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Mercedes-benz"));

        carModelRepository.save(new CarModel(new ObjectId(), "A", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "C", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CL", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CLK", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "GL", brandId));

        //Nissan
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Nissan"));

        carModelRepository.save(new CarModel(new ObjectId(), "Primera", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Silvia", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Skyline", brandId));

        //Skoda
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Skoda"));

        carModelRepository.save(new CarModel(new ObjectId(), "Octavia", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Filicia", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Kodiaq", brandId));

        //Peugeot
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Peugeot"));

        carModelRepository.save(new CarModel(new ObjectId(), "307", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "908", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "3008", brandId));

        //Subaru
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Subaru"));

        carModelRepository.save(new CarModel(new ObjectId(), "Impreza", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Legacy", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Forester", brandId));

        //Volkswagen
        brandId = new ObjectId();
        carBrandRepository.save(new CarBrand(brandId, "Volkswagen"));

        carModelRepository.save(new CarModel(new ObjectId(), "Passat", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "CC", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Golf", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Touareg", brandId));
        carModelRepository.save(new CarModel(new ObjectId(), "Tiguan", brandId));
    }


}
