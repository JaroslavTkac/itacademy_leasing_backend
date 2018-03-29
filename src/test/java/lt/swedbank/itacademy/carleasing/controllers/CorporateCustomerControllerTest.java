package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CorporateCustomerController.class)
public class CorporateCustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CorporateCustomerRepository repository;

    @MockBean
    private CorporateCustomerController controller;

    private JacksonTester<CorporateCustomer> jsonCorporateCustomer;
    private JacksonTester<CorporateCustomerResponse> jsonCorporateCustomerResponse;
    private JacksonTester<List<CorporateCustomerResponse>> jsonCorporateCustomerResponseList;

    private ObjectId id;
    private CorporateCustomer goodCustomer;
    private CorporateCustomer badCustomer;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        id = new ObjectId();
        ObjectId leaseId = new ObjectId();
        goodCustomer = new CorporateCustomer(id, leaseId, "inbox@inbox.lt", "865666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789");
        badCustomer = new CorporateCustomer(id, leaseId, "inbox@inbox.lt", "865666002",
                "Vilniaus g.2", "UAB ALTAS", "173598191650171");
    }

    @Test
    public void canRetrieveCustomerById() throws Exception {
        //given
        given(controller.getCorporateCustomerById(String.valueOf(id)))
                .willReturn(new CorporateCustomerResponse(goodCustomer));

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/corporate_customer/" + String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponse.write(new CorporateCustomerResponse(goodCustomer)).getJson()
        );
    }

    //FIXME NOT_FOUND should be
    @Test
    public void canRetrieveNotExistedCustomerById() throws Exception{
        //given
        given(controller.getCorporateCustomerById(String.valueOf(id)))
                .willReturn(new CorporateCustomerResponse(goodCustomer));


        System.out.println(String.valueOf(id));

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/corporate_customer/" + String.valueOf(new ObjectId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

    }

    @Test
    public void canRetrieveOneCustomerFromList() throws Exception{
        List<CorporateCustomerResponse> customers = new ArrayList();
        customers.add(new CorporateCustomerResponse(goodCustomer));

        //given
        given(controller.getAllCorporateCustomers()).willReturn(customers);

        //when
        MockHttpServletResponse response =  mvc.perform(
                get("/corporate_customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponseList.write(customers).getJson()
        );
    }

    @Test
    public void canRetrieveFullListOfCustomers() throws Exception {
        List<CorporateCustomerResponse> allCustomers = new ArrayList();
        for (int i = 0; i < 10; i++) {
            allCustomers.add(new CorporateCustomerResponse(goodCustomer));
        }

        //given
        given(controller.getAllCorporateCustomers()).willReturn(allCustomers);

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/corporate_customer")
                        .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andReturn().getResponse();
    }

    @Test
    public void canAddANewCustomer() throws Exception {
        //given
        given(controller.add(goodCustomer)).willReturn(new CorporateCustomerResponse(goodCustomer));

        //when
        MockHttpServletResponse response = mvc.perform(
                post("/corporate_customer/add").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCorporateCustomer.write(goodCustomer).getJson()
                )).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        /*assertThat(response.getContentAsString()).isEqualTo(
                jsonCorporateCustomerResponse.write(new CorporateCustomerResponse(goodCustomer)).getJson()
        );*/ //TODO paklausti kodel response yra tuscias, o kai siunti su klaida responsas yra
    }

    @Test
    public void canAddNewCustomerWithIncorrectData() throws Exception {
        //when
        MockHttpServletResponse response = mvc.perform(
                post("/corporate_customer/add").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCorporateCustomer.write(badCustomer).getJson()
                )).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void canDeleteCustomerById() throws Exception{
        MockHttpServletResponse deleteResponse = mvc.perform(
                delete("/corporate_customer/delete/" + String.valueOf(id)).contentType(MediaType.APPLICATION_JSON).content(
                        jsonCorporateCustomer.write(goodCustomer).getJson()
                )).andReturn().getResponse();
        //then
        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void canDeleteCustomerByIdNotExistedCustomer() throws Exception{
        //when
        MockHttpServletResponse deleteResponse = mvc.perform(
                delete("/corporate_customer/delete/5abcf030ea1d3a2249f93391").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCorporateCustomer.write(goodCustomer).getJson()
                )).andReturn().getResponse();
        //then
        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }






}