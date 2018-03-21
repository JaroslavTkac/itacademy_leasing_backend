package lt.swedbank.itacademy.carleasing.beans.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "car_models")
public class CarModel {

    @Id
    private ObjectId id;

    @NotNull
    private String model;

    @NotNull
    private ObjectId brandId;

    public CarModel() {
    }

    public CarModel(ObjectId id, @NotNull String model, @NotNull ObjectId brandId) {
        setId(id);
        setModel(model);
        setBrandId(brandId);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ObjectId getBrandId() {
        return brandId;
    }

    public void setBrandId(ObjectId brandId) {
        this.brandId = brandId;
    }
}
