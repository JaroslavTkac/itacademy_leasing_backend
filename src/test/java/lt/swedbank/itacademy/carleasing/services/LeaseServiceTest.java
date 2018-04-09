package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.*;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import lt.swedbank.itacademy.carleasing.repositories.LeaseRepository;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LeaseServiceTest {

    private MockMvc mockMvc;

    @Mock
    private LeaseRepository repository;

    @Mock
    private PrivateCustomerRepository privateCustomerRepository;

    @Mock
    private CorporateCustomerRepository corporateCustomerRepository;

    @InjectMocks
    private LeaseService service;

    private Lease goodLease;
    private List<Lease> goodLeaseList;
    private List<PrivateCustomer> privateCustomers;
    private List<CorporateCustomer> corporateCustomers;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ObjectId leaseId = new ObjectId();

        goodLease = new Lease(leaseId,
                "Private", "Vehicle", "Audi", "A3",
                "2005", 110, new BigDecimal(5000), 10,
                new BigDecimal(500), 84, 3.2f,
                new BigDecimal(200), 30, "Waiting");

        PrivateCustomer goodPrivateCustomer = new PrivateCustomer(new ObjectId(), leaseId, "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "K.Maslovo pr.12");

        CorporateCustomer goodCorporateCustomer = new CorporateCustomer(new ObjectId(), new ObjectId(), "inbox@inbox.lt", "+37065666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789");

        goodLeaseList = new ArrayList<>();
        goodLeaseList.add(goodLease);
        leaseId = new ObjectId();
        goodLeaseList.add(new Lease(leaseId,
                "Corporate", "Vehicle", "Audi", "A3",
                "2005", 110, new BigDecimal(10000), 10,
                new BigDecimal(1000), 84, 3.2f,
                new BigDecimal(200), 30, "Waiting"));

        privateCustomers = new ArrayList<>();
        privateCustomers.add(goodPrivateCustomer);

        corporateCustomers = new ArrayList<>();
        corporateCustomers.add(goodCorporateCustomer);
        corporateCustomers.add(new CorporateCustomer(new ObjectId(), leaseId, "inbox@inbox.lt", "+37065666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789"));
    }

    @Test
    public void canRetrieveAllLeases() throws Exception{
        when(repository.findAll()).thenReturn(goodLeaseList);

        List<LeaseResponse> result = service.getAllLeases();

        int i = 0;
        for (Lease lease: goodLeaseList) {
            Assert.assertEquals(new LeaseResponse(lease).getCarBrand(), result.get(i).getCarBrand());
            i++;
        }
    }

    @Test
    public void canAddNewLease() throws Exception{
        when(repository.save(any())).thenReturn(goodLease);

        LeaseResponse result = service.addNewLease(goodLease);

        Assert.assertEquals(new LeaseResponse(goodLease).getCarBrand(), result.getCarBrand());
    }

    @Test
    public void canUpdateLeaseStatusWithAccepted() throws Exception{
        when(repository.findLeasingById(String.valueOf(goodLease.getId()))).thenReturn(goodLease);
        when(repository.save(any())).thenReturn(goodLease);

        LeaseResponse result = service.updateStatus(String.valueOf(goodLease.getId()), "Accepted");

        Assert.assertEquals("Accepted", result.getStatus());
    }

    @Test
    public void canUpdateLeaseStatusWithRejected() throws Exception{
        when(repository.findLeasingById(String.valueOf(goodLease.getId()))).thenReturn(goodLease);
        when(repository.save(any())).thenReturn(goodLease);

        LeaseResponse result = service.updateStatus(String.valueOf(goodLease.getId()), "Rejected");

        Assert.assertEquals("Rejected", result.getStatus());
    }

    @Test(expected = NotFoundException.class)
    public void canUpdateLeaseStatusOfNonExistedLease() throws Exception{
        when(repository.findLeasingById(String.valueOf(goodLease.getId()))).thenReturn(null);
        when(repository.save(any())).thenReturn(goodLease);

        LeaseResponse result = service.updateStatus(String.valueOf(goodLease.getId()), "Accepted");
    }

    @Test
    public void canRetrieveLeaseWithPrivateCustomer() throws Exception{
        when(repository.findLeasingById(String.valueOf(goodLease.getId()))).thenReturn(goodLease);
        when(privateCustomerRepository.findAll()).thenReturn(privateCustomers);
        when(corporateCustomerRepository.findAll()).thenReturn(corporateCustomers);

        CustomerLease result = service.getLeaseWithCustomer(String.valueOf(goodLease.getId()));

        Assert.assertEquals(new CustomerLease(new PrivateCustomerResponse(privateCustomers.get(0)), new LeaseResponse(goodLease))
                        .getCustomer().getPhoneNumber(), result.getCustomer().getPhoneNumber());
    }

    @Test
    public void canRetrieveLeaseWithCorporateCustomer() throws Exception{
        when(repository.findLeasingById(String.valueOf(goodLeaseList.get(1).getId()))).thenReturn(goodLeaseList.get(1));
        when(privateCustomerRepository.findAll()).thenReturn(privateCustomers);
        when(corporateCustomerRepository.findAll()).thenReturn(corporateCustomers);

        CustomerLease result = service.getLeaseWithCustomer(String.valueOf(goodLeaseList.get(1).getId()));

        Assert.assertEquals(new CustomerLease(new CorporateCustomerResponse(corporateCustomers.get(1)), new LeaseResponse(goodLeaseList.get(1)))
                .getCustomer().getPhoneNumber(), result.getCustomer().getPhoneNumber());
    }

    @Test(expected = NotFoundException.class)
    public void canRetrieveNotExistedLeaseWithCustomer() throws Exception{
        when(repository.findLeasingById(String.valueOf(new ObjectId()))).thenReturn(null);
        when(privateCustomerRepository.findAll()).thenReturn(privateCustomers);
        when(corporateCustomerRepository.findAll()).thenReturn(corporateCustomers);

        service.getLeaseWithCustomer(String.valueOf(goodLease.getId()));

    }


}