package lt.swedbank.itacademy.carleasing.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.services.PrivateCustomerService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PrivateCustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PrivateCustomerService service;

    @InjectMocks
    private PrivateCustomerController controller;


    private JacksonTester<PrivateCustomer> jsonPrivateCustomer;
    private JacksonTester<PrivateCustomerResponse> jsonPrivateCustomerResponse;
    private JacksonTester<List<PrivateCustomerResponse>> jsonPrivateCustomerResponseList;

    private ObjectId id;
    private PrivateCustomer goodCustomer;
    private PrivateCustomer badCustomer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        id = new ObjectId();

        goodCustomer = new PrivateCustomer(id, new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");
        badCustomer = new PrivateCustomer(id, new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "+371865090090", "Vilniaus g.2");
    }

    @Test
    public void canRetrieveAllCustomers() throws Exception {
        List<PrivateCustomerResponse> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            goodCustomer.setFirstName(goodCustomer.getFirstName() + String.valueOf(i));
            customers.add(new PrivateCustomerResponse(goodCustomer));
        }

        //given
        given(controller.getAllPrivateCustomers()).willReturn(customers);

        //when
        mockMvc.perform(
                get("/private-customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))

                .andExpect(jsonPath("$[0].firstName", is("Jaroslav0")))
                .andExpect(jsonPath("$[1].firstName", is("Jaroslav01")))
                .andExpect(jsonPath("$[2].firstName", is("Jaroslav012")))
                .andExpect(jsonPath("$[3].firstName", is("Jaroslav0123")))
                .andExpect(jsonPath("$[4].firstName", is("Jaroslav01234")))
                .andReturn().getResponse();
    }

    @Test
    public void canRetrieveOneCustomerFromList() throws Exception{
        List<PrivateCustomerResponse> customers = new ArrayList<>();
        customers.add(new PrivateCustomerResponse(goodCustomer));

        //given
        given(controller.getAllPrivateCustomers()).willReturn(customers);

        //when
        MockHttpServletResponse response =  mockMvc.perform(
                get("/private-customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonPrivateCustomerResponseList.write(customers).getJson()
        );
    }

    @Test
    public void canRetrieveCustomerById() throws Exception {
        //given
        given(controller.getPrivateCustomerById(String.valueOf(id)))
                .willReturn(new PrivateCustomerResponse(goodCustomer));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/private-customer/" + String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonPrivateCustomerResponse.write(new PrivateCustomerResponse(goodCustomer)).getJson()
        );
    }

    @Test
    public void canRetrieveNotExistedCustomerById() throws Exception{
        //given
        when(controller.getPrivateCustomerById(any()))
                .thenThrow(new NotFoundException(""));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/private-customer/1" )
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
    }

//    @Test
//    public void canAddNewCustomer() throws Exception {
//        when(controller.addNewPrivateCustomer(any())).thenReturn(new PrivateCustomerResponse(goodCustomer));
//
//        //when
//        MockHttpServletResponse response = mockMvc.perform(
//                post("/private_customer/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonPrivateCustomer.write(goodCustomer).getJson()
//                        ))
//                //then
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//
//        assertThat(response.getContentAsString()).isEqualTo(
//                jsonPrivateCustomerResponse.write(new PrivateCustomerResponse(goodCustomer)).getJson()
//        );
//    }

//    @Test
//    public void canAddNewCustomerWithIncorrectData() throws Exception {
//        //when
//        mockMvc.perform(
//                post("/private_customer/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonPrivateCustomer.write(badCustomer).getJson()
//                        ))
//                //then
//                .andExpect(status().isBadRequest())
//                .andReturn().getResponse();
//    }

    @Test
    public void canDeleteCustomerById() throws Exception{
        //when
        mockMvc.perform(
                delete("/private-customer/delete/" + String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }


}