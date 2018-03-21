package lt.swedbank.itacademy.carleasing.beans.documents;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private CarBrand carBrand;
    private List<CarModel>  carModel;


    Car(CarBrand carBrand, List<CarModel> carModels){
        setCarModel(new ArrayList<>());
        setCarBrand(carBrand);

        for (CarModel model: carModels){
            carModel.add(model);
        }
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public List<CarModel> getCarModel() {
        return carModel;
    }

    public void setCarModel(List<CarModel> carModel) {
        this.carModel = carModel;
    }
}
