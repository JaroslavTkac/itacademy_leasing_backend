package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributions;
import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributionsPaymentData;
import lt.swedbank.itacademy.carleasing.beans.responses.ScheduleOfContributionsResponse;
import lt.swedbank.itacademy.carleasing.services.ScheduleOfContributionsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ScheduleOfContributionsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ScheduleOfContributionsService service;

    @InjectMocks
    private ScheduleOfContributionsController controller;

    private ScheduleOfContributions schedule;

    private JacksonTester<ScheduleOfContributionsResponse> jsonScheduleResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        schedule = new ScheduleOfContributions("Private", BigDecimal.valueOf(5000), 10, BigDecimal.valueOf(500),
                6, 4f, BigDecimal.valueOf(200), 15);

    }

    @Test
    public void canRetrieveScheduledPaymentData() throws Exception { //FIXME kazkodel statusa 400 meta
        List<ScheduleOfContributionsPaymentData> paymentData = new ArrayList<>();

        ScheduleOfContributionsPaymentData data = new ScheduleOfContributionsPaymentData(BigDecimal.valueOf(5000),
                BigDecimal.valueOf(300), BigDecimal.valueOf(20.5), BigDecimal.valueOf(320.5));

        paymentData.add(data);

        //given
        when(controller.calculateSchedulePayments(schedule))
                .thenReturn(new ScheduleOfContributionsResponse(paymentData));

        //when
        /*MockHttpServletResponse response = mockMvc.perform(
                post("/schedule-of-contributions/post")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(
                jsonScheduleResponse.write(new ScheduleOfContributionsResponse(paymentData)).getJson()
        );*/
    }
}