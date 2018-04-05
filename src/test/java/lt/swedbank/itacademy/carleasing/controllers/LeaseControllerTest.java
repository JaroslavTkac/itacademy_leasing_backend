package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
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

    private JacksonTester<LeaseResponse> jsonLeaseResponse;
    private JacksonTester<Lease> jsonLease;

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
    }

    @Test
    public void canRetrieveAllCustomers() throws Exception {
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