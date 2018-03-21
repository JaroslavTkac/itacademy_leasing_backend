package lt.swedbank.itacademy.carleasing.beans.documents;

import lt.swedbank.itacademy.carleasing.beans.responses.CarBrandResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CarModelResponse;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private CarBrandResponse carBrand;  //BMW, Audi, Subaru etc...
    private List<CarModelResponse>  carModel; //A1, 525, Forester


    public Car(CarBrandResponse carBrand, List<CarModelResponse> carModels){
        setCarModel(new ArrayList<>());
        setCarBrand(carBrand);

        for (CarModelResponse model: carModels){
            carModel.add(model);
        }
    }

    public CarBrandResponse getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandResponse carBrand) {
        this.carBrand = carBrand;
    }

    public List<CarModelResponse> getCarModel() {
        return carModel;
    }

    public void setCarModel(List<CarModelResponse> carModel) {
        this.carModel = carModel;
    }
}
