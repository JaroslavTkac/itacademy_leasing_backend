package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.services.CorporateCustomerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CorporateCustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CorporateCustomerService service;

    @InjectMocks
    private CorporateCustomerController controller;


    private JacksonTester<CorporateCustomer> jsonCorporateCustomer;
    private JacksonTester<CorporateCustomerResponse> jsonCorporateCustomerResponse;
    private JacksonTester<List<CorporateCustomerResponse>> jsonCorporateCustomerResponseList;

    private ObjectId id;
    private CorporateCustomer goodCustomer;
    private CorporateCustomer badCustomer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

                //.setControllerAdvice(new RestResponseEntityExceptionHandler())

        id = new ObjectId();

        goodCustomer = new CorporateCustomer(id, new ObjectId(), "inbox@inbox.lt", "865666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789");
        badCustomer = new CorporateCustomer(id, new ObjectId(), "inbox@inbox.lt", "865666002",
                "Vilniaus g.2", "UAB ALTAS", "173598191650171");

    }

    @Test
    public void canRetrieveAllCustomers() throws Exception{
        List<CorporateCustomerResponse> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            goodCustomer.setCompanyName(goodCustomer.getCompanyName() + String.valueOf(i));
            customers.add(new CorporateCustomerResponse(goodCustomer));
        }

        when(controller.getAllCorporateCustomers()).thenReturn(customers);

        mockMvc.perform(
                get("/corporate_customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))

                .andExpect(jsonPath("$[0].companyName", is("UAB ALTAS0")))
                .andExpect(jsonPath("$[1].companyName", is("UAB ALTAS01")))
                .andExpect(jsonPath("$[2].companyName", is("UAB ALTAS012")))
                .andExpect(jsonPath("$[3].companyName", is("UAB ALTAS0123")))
                .andExpect(jsonPath("$[4].companyName", is("UAB ALTAS01234")))
                .andReturn().getResponse();
    }

    @Test
    public void canRetrieveOneCustomerFromList() throws Exception{
        List<CorporateCustomerResponse> customers = new ArrayList<>();
        customers.add(new CorporateCustomerResponse(goodCustomer));

        //given
        given(controller.getAllCorporateCustomers()).willReturn(customers);

        //when
        MockHttpServletResponse response =  mockMvc.perform(
                get("/corporate_customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponseList.write(customers).getJson()
        );
    }

    @Test
    public void canRetrieveCustomerById() throws Exception {
        //given
        given(controller.getCorporateCustomerById(String.valueOf(id)))
                .willReturn(new CorporateCustomerResponse(goodCustomer));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/corporate_customer/" + String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponse.write(new CorporateCustomerResponse(goodCustomer)).getJson()
        );
    }

    @Test
    public void canRetrieveNotExistedCustomerById() throws Exception{
        when(controller.getCorporateCustomerById(any()))
                .thenThrow(new NotFoundException(""));

        //when
        mockMvc.perform(
                get("/corporate_customer/" + String.valueOf(new ObjectId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
    }

    @Test
    public void canAddNewCustomer() throws Exception {
        when(controller.add(any()))
                .thenReturn(new CorporateCustomerResponse(goodCustomer));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                post("/corporate_customer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCorporateCustomer.write(goodCustomer).getJson()
                ))
                //then
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponse.write(new CorporateCustomerResponse(goodCustomer)).getJson()
        );
    }

    @Test
    public void canAddNewCustomerWithIncorrectData() throws Exception {
        //when
        mockMvc.perform(
                post("/corporate_customer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCorporateCustomer.write(badCustomer).getJson()
                ))
                //then
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
    }

    @Test
    public void canDeleteCustomerById() throws Exception{
        //when
        MockHttpServletResponse response = mockMvc.perform(
                delete("/corporate_customer/delete/" + String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }




}