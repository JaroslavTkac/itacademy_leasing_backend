package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.CarModel;

public class CarModelResponse extends Response {

    private String id;
    private String model;
    private String brandId;



    public CarModelResponse(CarModel carModel) {
        setId(String.valueOf(carModel.getId()));
        setModel(carModel.getModel());
        setBrandId(String.valueOf(carModel.getBrandId()));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
