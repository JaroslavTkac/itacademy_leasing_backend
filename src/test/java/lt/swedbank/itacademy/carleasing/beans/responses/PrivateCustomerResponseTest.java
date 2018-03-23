package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PrivateCustomerResponseTest {

    private PrivateCustomerResponse response;

    @Before
    public void setUp(){
    }

    @Test
    public void capitalizationTestingCorrectInput(){
        response = new PrivateCustomerResponse(new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2", new ArrayList<>()));
        assertEquals("Jaroslav", response.getFirstName());
    }

    @Test
    public void capitalizationTestingFirstNameWithNotCapitalizedFirstLetter(){
        response = new PrivateCustomerResponse(new PrivateCustomer(new ObjectId(), new ObjectId(), "jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2", new ArrayList<>()));
        assertEquals("Jaroslav", response.getFirstName());
    }

}