package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.CarBrand;

public class CarBrandResponse extends Response {


    private String id;
    private String brand;

    public CarBrandResponse(CarBrand carBrand) {
        setId(String.valueOf(carBrand.getId()));
        setBrand(carBrand.getBrand());
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
