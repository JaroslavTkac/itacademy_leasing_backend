package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class CompleteLeaseServiceTest {

    private MockMvc mockMvc;

    @Mock
    private LeaseService leaseService;

    @Mock
    private PrivateCustomerService privateCustomerService;

    @Mock
    private CorporateCustomerService corporateCustomerService;

    @InjectMocks
    private CompleteLeaseService service;

    private PrivateCustomer goodPrivateCustomer;
    private CorporateCustomer goodCorporateCustomer;
    private Lease goodLease;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


        goodPrivateCustomer = new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "K.Maslovo pr.12");

        goodCorporateCustomer = new CorporateCustomer(new ObjectId(), new ObjectId(), "inbox@inbox.lt", "+37065666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789");

        goodLease = new Lease(new ObjectId(),
                "Private", "Vehicle", "Audi", "A3",
                "2005", 110, new BigDecimal(5000), 10,
                new BigDecimal(500), 84, 3.2f,
                new BigDecimal(200), 30, "Accepted");

    }


    @Test
    public void canAddNewCompleteLeaseWithPrivateCustomer() throws Exception{
        goodLease.setLeaseType("Private");
        when(leaseService.addNewLease(goodLease)).thenReturn(new LeaseResponse(goodLease));
        when(privateCustomerService.addNewPrivateCustomer(goodPrivateCustomer)).thenReturn(new PrivateCustomerResponse(goodPrivateCustomer));

        CompleteLeaseResponse result = service.addNewCompleteLease(new CompleteLease(goodLease, goodPrivateCustomer));

        Assert.assertEquals(goodPrivateCustomer.getAddress(), result.getAddress());
        Assert.assertEquals(goodLease.getAssetPrice(), result.getAssetPrice());

    }

    @Test
    public void canAddNewCompleteLeaseWithCorporateCustomer() throws Exception{
        goodLease.setLeaseType("Corporate");
        when(leaseService.addNewLease(goodLease)).thenReturn(new LeaseResponse(goodLease));
        when(corporateCustomerService.addNewCorporateCustomer(goodCorporateCustomer)).thenReturn(new CorporateCustomerResponse(goodCorporateCustomer));

        CompleteLeaseResponse result = service.addNewCompleteLease(new CompleteLease(goodLease, goodCorporateCustomer));

        Assert.assertEquals(goodCorporateCustomer.getAddress(), result.getAddress());
        Assert.assertEquals(goodLease.getAssetPrice(), result.getAssetPrice());

    }



}