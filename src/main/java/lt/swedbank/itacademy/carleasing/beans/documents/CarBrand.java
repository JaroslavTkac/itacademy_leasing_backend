package lt.swedbank.itacademy.carleasing.beans.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "car_brands")
public class CarBrand {

    @Id
    private ObjectId id;

    @NotNull
    private String brand;


    public CarBrand() {
    }

    public CarBrand(ObjectId id, @NotNull String brand){
        setId(id);
        setBrand(brand);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
