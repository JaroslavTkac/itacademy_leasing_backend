package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.Car;
import lt.swedbank.itacademy.carleasing.beans.documents.CarBrand;
import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;
import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.CarBrandRepository;
import lt.swedbank.itacademy.carleasing.repositories.CarModelRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CarsServiceTest {

    private MockMvc mockMvc;

    @Mock
    private CarBrandRepository brandRepository;

    @Mock
    private CarModelRepository modelRepository;

    @InjectMocks
    private CarsService service;

    private List<CarBrand> carBrands;
    private List<CarModel> carModels;
    private List<Car> allCars;

    ObjectId alfaRomeoId;
    ObjectId audiId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        carBrands = new ArrayList<>();
        carModels = new ArrayList<>();
        allCars = new ArrayList<>();

        alfaRomeoId = new ObjectId();
        audiId = new ObjectId();

        carBrands.add(new CarBrand(alfaRomeoId, "Alfa Romeo"));
        carBrands.add(new CarBrand(audiId, "Audi"));

        carModels.add(new CarModel(new ObjectId(), "147", alfaRomeoId));
        carModels.add(new CarModel(new ObjectId(), "155", alfaRomeoId));
        carModels.add(new CarModel(new ObjectId(), "Giulia", alfaRomeoId));

        carModels.add(new CarModel(new ObjectId(), "A2", audiId));
        carModels.add(new CarModel(new ObjectId(), "A3", audiId));
        carModels.add(new CarModel(new ObjectId(), "A4", audiId));

        List<CarModelResponse> alfaRomeoModels = new ArrayList<>();
        alfaRomeoModels.add(new CarModelResponse(carModels.get(0)));
        alfaRomeoModels.add(new CarModelResponse(carModels.get(1)));
        alfaRomeoModels.add(new CarModelResponse(carModels.get(2)));

        allCars.add(new Car(new CarBrandResponse(carBrands.get(0)), alfaRomeoModels));

        List<CarModelResponse> audiModels = new ArrayList<>();
        audiModels.add(new CarModelResponse(carModels.get(3)));
        audiModels.add(new CarModelResponse(carModels.get(4)));
        audiModels.add(new CarModelResponse(carModels.get(5)));

        allCars.add(new Car(new CarBrandResponse(carBrands.get(1)), audiModels));

    }

    @Test
    public void canRetrieveAllBrands() throws Exception {
        List<CarBrandResponse> carBrandResponse = new ArrayList<>();
        for (CarBrand brand : carBrands) {
            carBrandResponse.add(new CarBrandResponse(brand));
        }

        when(brandRepository.findAll()).thenReturn(carBrands);

        List<CarBrandResponse> result = service.getAllBrands();

        int i = 0;
        for (CarBrand brand : carBrands) {
            Assert.assertEquals(brand.getBrand(), result.get(i).getBrand());
            i++;
        }
    }

    @Test
    public void canRetrieveAllModelsByBrand() throws Exception {
        List<CarModel> audiCarModels = new ArrayList<>();
        for (CarModel model : carModels) {
            if (model.getBrandId().equals(audiId)) {
                audiCarModels.add(model);
            }
        }

        when(brandRepository.findCarBrandsByBrand("Audi")).thenReturn(new CarBrand(audiId, "Audi"));
        when(modelRepository.findCarModelsByBrandId(audiId)).thenReturn(audiCarModels);

        List<CarModelResponse> result = service.getAllModelsByBrand("Audi");


        for (int i = 3; i < carModels.size(); i++) {
            Assert.assertEquals(carModels.get(i).getModel(), result.get(i - 3).getModel());
        }
    }


    @Test(expected = NotFoundException.class)
    public void canRetrieveAllModelsByBrandShouldThrowNotFoundException() throws Exception {
        List<CarModel> audiCarModels = new ArrayList<>();
        for (CarModel model : carModels) {
            if (model.getBrandId().equals(audiId)) {
                audiCarModels.add(model);
            }
        }

        when(brandRepository.findCarBrandsByBrand("Audi")).thenReturn(null);
        when(modelRepository.findCarModelsByBrandId(audiId)).thenReturn(audiCarModels);

        List<CarModelResponse> result = service.getAllModelsByBrand("Audi");
    }

    @Test
    public void canRetrieveAllBrandsWithModels() throws Exception {
        when(brandRepository.findAll()).thenReturn(carBrands);
        when(modelRepository.findAll()).thenReturn(carModels);

        List<Car> result = service.getAllCars();

        for (int i = 0; i < allCars.size(); i++) {
            Assert.assertEquals(allCars.get(i).getCarBrand().getBrand(),
                    result.get(i).getCarBrand().getBrand());
            for (int j = 0; j < allCars.get(i).getCarModel().size(); j++) {
                Assert.assertEquals(allCars.get(i).getCarModel().get(j).getModel(),
                        result.get(i).getCarModel().get(j).getModel());
            }
        }

    }

}