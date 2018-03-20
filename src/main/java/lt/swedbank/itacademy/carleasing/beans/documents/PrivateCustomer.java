package lt.swedbank.itacademy.carleasing.beans.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "private_customers")
public class PrivateCustomer {

    @Id
    private ObjectId id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String personalCode;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    private List errorCodes;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List errorCodes) {
        this.errorCodes = errorCodes;
    }
}
