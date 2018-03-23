package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PrivateCustomerController.class)
public class PrivateCustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PrivateCustomerController controller;

    private ObjectId privateCustomerId;
    private ObjectId leaseId;

    @Before
    public void setUp() throws Exception {
        privateCustomerId = new ObjectId();
        leaseId = new ObjectId();
    }

    @Test
    public void getAllPrivateCustomersTestingAllFields() throws Exception {
        PrivateCustomer privateCustomer = new PrivateCustomer(privateCustomerId, leaseId, "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2", new ArrayList<>());

        List<PrivateCustomerResponse> allPrivateCustomers = new ArrayList();
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));

        given(controller.getAllPrivateCustomers()).willReturn(allPrivateCustomers);

        mvc.perform(get("/private_customer")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(privateCustomer.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(privateCustomer.getLastName())))
                .andExpect(jsonPath("$[0].personalCode", is(privateCustomer.getPersonalCode())))
                .andExpect(jsonPath("$[0].email", is(privateCustomer.getEmail())))
                .andExpect(jsonPath("$[0].phoneNumber", is(privateCustomer.getPhoneNumber())))
                .andExpect(jsonPath("$[0].address", is(privateCustomer.getAddress())))
                .andExpect(jsonPath("$[0].id", is(String.valueOf(privateCustomer.getId()))))
                .andExpect(jsonPath("$[0].leaseId", is(String.valueOf(privateCustomer.getLeaseId()))));
    }

    @Test
    public void getAllPrivateCustomersTestingCorrectAmountOfElements() throws Exception {
        PrivateCustomer privateCustomer = new PrivateCustomer(privateCustomerId, leaseId, "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2", new ArrayList<>());

        List<PrivateCustomerResponse> allPrivateCustomers = new ArrayList();
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));
        allPrivateCustomers.add(new PrivateCustomerResponse(privateCustomer));

        given(controller.getAllPrivateCustomers()).willReturn(allPrivateCustomers);

        mvc.perform(get("/private_customer")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void addNewPrivateCustomer() throws Exception {
        PrivateCustomer privateCustomer = new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");

        //PrivateCustomerResponse response = new PrivateCustomerResponse(privateCustomer);

        //given(controller.addNewPrivateCustomer(privateCustomer)).willReturn(response);

        System.out.println("SENDING : " + asJsonString(privateCustomer));

        mvc.perform(post("/private_customer/add")
                .content(asJsonString(privateCustomer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    System.out.println("RECEIVING : " + mvcResult.getResponse());

                    //ObjectMapper mapper = new ObjectMapper();
                    //PrivateCustomer response = mapper.readValue(asJsonString(privateCustomer), PrivateCustomer.class);

                    //System.out.println(response.getEmail());
                    //Verrify Response here
                });
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}