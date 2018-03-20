package lt.swedbank.itacademy.carleasing.beans.documents;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    private CarBrands carBrand;
    private List<CarModels>  carModel;


    Cars(CarBrands carBrand, List<CarModels> carModels){
        setCarModel(new ArrayList<>());
        setCarBrand(carBrand);

        for (CarModels model: carModels){
            carModel.add(model);
        }
    }

    public CarBrands getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrands carBrand) {
        this.carBrand = carBrand;
    }

    public List<CarModels> getCarModel() {
        return carModel;
    }

    public void setCarModel(List<CarModels> carModel) {
        this.carModel = carModel;
    }
}
