package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.CustomerLease;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.IllegalParameterException;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.services.LeaseService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LeaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LeaseService service;

    @InjectMocks
    private LeaseController controller;

    private Lease goodLease;
    private Lease badLease;
    private CustomerLease customerLease;
    private PrivateCustomer customer;

    private JacksonTester<LeaseResponse> jsonLeaseResponse;
    private JacksonTester<Lease> jsonLease;
    private JacksonTester<CustomerLease> jsonCustomerLease;
    private JacksonTester<List<CustomerLease>> jsonCustomerLeaseList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        goodLease = new Lease(new ObjectId(),
                "Private", "Vehicle", "Audi", "A3",
                "2005", 110, new BigDecimal(5000), 10,
                new BigDecimal(500), 84, 3.2f,
                new BigDecimal(200), 30, "Accepted");

        badLease = new Lease(new ObjectId(),
                "Private", "Vehicle", "Audi", "A3",
                "1899", 110, new BigDecimal(5000), 10,
                new BigDecimal(500), 84, 3.2f,
                new BigDecimal(200), 30, "Accepted");

        customer =  new PrivateCustomer(new ObjectId(), goodLease.getId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");

        customerLease = new CustomerLease(new PrivateCustomerResponse(customer), new LeaseResponse(goodLease));
    }

    @Test
    public void canRetrieveAllLeases() throws Exception {
        List<LeaseResponse> leases = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            leases.add(new LeaseResponse(goodLease));
        }

        //given
        given(controller.getAllLeases()).willReturn(leases);

        //when
        mockMvc.perform(
                get("/lease")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andReturn().getResponse();
    }

    @Test
    public void canUpdateStatus() throws Exception {
        //given
        when(controller.updateStatus(String.valueOf(goodLease.getId()), "accepted"))
                .thenReturn(new LeaseResponse(goodLease));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                put("/lease/" + String.valueOf(goodLease.getId()) + "/status/accepted")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonLeaseResponse.write(new LeaseResponse(goodLease)).getJson()
        );
    }

    @Test
    public void canUpdateStatusWithIncorrectInput() throws Exception {
        //given
        when(controller.updateStatus(String.valueOf(goodLease.getId()), "approved"))
                .thenThrow(new IllegalParameterException(""));

        //when
        mockMvc.perform(
                put("/lease/" + String.valueOf(goodLease.getId()) + "/status/approved")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

    }

    @Test
    public void canUpdateNotExistedLeaseStatus() throws Exception {
        //given
        when(controller.updateStatus(String.valueOf(goodLease.getId()), "accepted"))
                .thenThrow(new NotFoundException(""));

        //when
        mockMvc.perform(
                put("/lease/" + String.valueOf(goodLease.getId()) + "/status/accepted")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();

    }

    @Test
    public void canRetrieveLeaseWithCustomer() throws Exception {
        //given
        when(controller.getLeaseWithCustomer(String.valueOf(goodLease.getId())))
                .thenReturn(customerLease);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/lease/" + String.valueOf(goodLease.getId()) + "/with-customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCustomerLease.write(customerLease).getJson()
        );
    }

    @Test
    public void canRetrieveNotExistedLeaseWithCustomer() throws Exception {
        //given
        when(controller.getLeaseWithCustomer(String.valueOf(goodLease.getId())))
                .thenThrow(new NotFoundException(""));

        //when
        mockMvc.perform(
                get("/lease/" + String.valueOf(goodLease.getId()) + "/with-customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
    }

    @Test
    public void canRetrieveAllLeasesWithCustomers() throws Exception {
        List<CustomerLease> customerLeases = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customerLeases.add(customerLease);
        }

        //given
        when(controller.getAllLeasesWithCustomers())
                .thenReturn(customerLeases);

        //when
        mockMvc.perform(
                get("/lease/detailed-leases")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andReturn().getResponse();
    }

//    @Test
//    public void canAddNewLease() throws Exception{
//        when(controller.addNewLease(any()))
//                .thenReturn(new LeaseResponse(goodLease));
//
//        //when
//        MockHttpServletResponse response = mockMvc.perform(
//                post("/leasing/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonLease.write(goodLease).getJson()
//                        ))
//                //then
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//
//        assertThat(response.getContentAsString()).isEqualTo(
//                jsonLeaseResponse.write(new LeaseResponse(goodLease)).getJson()
//        );
//    }

//    @Test
//    public void canAddNewLeaseIncorrect() throws Exception{
//        //when
//        MockHttpServletResponse response = mockMvc.perform(
//                post("/leasing/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonLease.write(badLease).getJson()
//                        ))
//                //then
//                .andExpect(status().isBadRequest())
//                .andReturn().getResponse();
//    }

    @Test
    public void canDeleteLeaseById() throws Exception{
        //when
        MockHttpServletResponse response = mockMvc.perform(
                delete("/lease/delete/" + String.valueOf(new ObjectId()))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }


}