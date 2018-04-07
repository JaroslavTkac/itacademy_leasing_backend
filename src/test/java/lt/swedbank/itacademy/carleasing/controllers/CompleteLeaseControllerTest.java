package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import lt.swedbank.itacademy.carleasing.exceptions.IllegalParameterException;
import lt.swedbank.itacademy.carleasing.services.CompleteLeaseService;
import org.assertj.core.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompleteLeaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompleteLeaseService service;

    @InjectMocks
    private CompleteLeaseController controller;

    private CompleteLease goodCompleteLease;
    private Lease goodLease;
    private PrivateCustomer goodPrivateCustomer;
    private CompleteLease badLease;

    private JacksonTester<CompleteLeaseResponse> jsonCompleteLeaseResponse;
    private JacksonTester<CompleteLease> jsonCompleteLease;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        goodPrivateCustomer = new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "K.Maslovo pr.12");

        goodLease = new Lease(new ObjectId(),
                "Private", "Vehicle", "Audi", "A3",
                "2005", 110, new BigDecimal(5000), 10,
                new BigDecimal(500), 84, 3.2f,
                new BigDecimal(200), 30, "Accepted");

        goodCompleteLease = new CompleteLease(goodLease, goodPrivateCustomer);

    }


    @Test
    public void canAddNewLease() throws Exception {
        when(controller.addNewLease(any()))
                .thenReturn(new CompleteLeaseResponse(goodCompleteLease));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                post("/complete-lease/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCompleteLease.write(goodCompleteLease).getJson()
                        ))
                //then
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jsonCompleteLeaseResponse.write(new CompleteLeaseResponse(goodCompleteLease)).getJson()
        );
    }

    @Test
    public void canAddNewIncorrectLease() throws Exception {
        goodCompleteLease.getLease().setApplicationDate("16");
        when(controller.addNewLease(any()))
                .thenThrow(new IllegalParameterException("Validation error"));

        //when
        mockMvc.perform(
                post("/complete-lease/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCompleteLease.write(goodCompleteLease).getJson()
                        ))
                //then
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

    }


}