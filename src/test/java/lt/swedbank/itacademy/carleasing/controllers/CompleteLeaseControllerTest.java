package lt.swedbank.itacademy.carleasing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import lt.swedbank.itacademy.carleasing.services.CompleteLeaseService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CompleteLeaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompleteLeaseService service;

    @InjectMocks
    private CompleteLeaseController controller;

    private CompleteLease goodLease;
    private CompleteLease badLease;

    private JacksonTester<CompleteLeaseResponse> jsonLeaseResponse;
    private JacksonTester<CompleteLease> jsonLease;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }

    


}