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
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
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

    private JacksonTester<PrivateCustomer> jsonPrivateCustomer;
    private JacksonTester<PrivateCustomerResponse> jsonPrivateCustomerResponse;
    private JacksonTester<List<PrivateCustomerResponse>> jsonPrivateCustomerResponseList;

    private ObjectId privateCustomerId;
    private ObjectId leaseId;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        privateCustomerId = new ObjectId();
        leaseId = new ObjectId();
    }


    @Test
    public void canRetrieveOneCustomerFromList() throws Exception {
        PrivateCustomer customer = new PrivateCustomer(privateCustomerId, leaseId, "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");

        List<PrivateCustomerResponse> customers = new ArrayList();
        customers.add(new PrivateCustomerResponse(customer));

        //given
        given(controller.getAllPrivateCustomers()).willReturn(customers);

        //when
        MockHttpServletResponse response =  mvc.perform(
                get("/private_customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonPrivateCustomerResponseList.write(customers).getJson()
        );

    }

    @Test
    public void canRetrieveFullListOfCustomers() throws Exception {
        PrivateCustomer customer = new PrivateCustomer(privateCustomerId, leaseId, "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");

        List<PrivateCustomerResponse> allCustomers = new ArrayList();
        for (int i = 0; i < 10; i++) {
            allCustomers.add(new PrivateCustomerResponse(customer));
        }

        //given
        given(controller.getAllPrivateCustomers()).willReturn(allCustomers);

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/private_customer")
                        .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andReturn().getResponse();
    }



    @Test
    public void addNewPrivateCustomer() throws Exception {
        PrivateCustomer privateCustomer = new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");


        System.out.println("SENDING : " + asJsonString(privateCustomer));

        mvc.perform(post("/private_customer/add")
                .content(asJsonString(privateCustomer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    //Verrify Response here

                    System.out.println("RECEIVING : " + mvcResult.getResponse());

                    ObjectMapper mapper = new ObjectMapper();
                    PrivateCustomer response = mapper.readValue(asJsonString(privateCustomer), PrivateCustomer.class);

                    //System.out.println(response.getEmail());
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