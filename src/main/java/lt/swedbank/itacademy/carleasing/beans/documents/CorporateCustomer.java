package lt.swedbank.itacademy.carleasing.beans.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "corporate_customers")
public class CorporateCustomer {

    @Id
    private ObjectId id;

    private List errorCodes;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List errorCodes) {
        this.errorCodes = errorCodes;
    }
}
