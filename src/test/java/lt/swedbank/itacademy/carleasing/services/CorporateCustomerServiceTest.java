package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.CorporateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.CorporateCustomerRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CorporateCustomerServiceTest {

    private MockMvc mockMvc;

    @Mock
    private CorporateCustomerRepository repository;

    @InjectMocks
    private CorporateCustomerService service;

    private CorporateCustomer goodCustomer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        goodCustomer = new CorporateCustomer(new ObjectId(), new ObjectId(), "inbox@inbox.lt", "+37065666002",
                "Vilniaus g.2", "UAB ALTAS", "123456789");
    }

    @Test
    public void canRetrieveAllCustomers() throws Exception{
        List<CorporateCustomer> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customers.add(goodCustomer);
        }

        when(repository.findAll()).thenReturn(customers);

        List<CorporateCustomerResponse> resultList = service.getAllCorporateCustomers();

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(customers.get(i).getCompanyName(), resultList.get(i).getCompanyName());
        }
    }

    @Test
    public void canAddNewCustomer() throws Exception{
        when(repository.save(any())).thenReturn(goodCustomer);

        CorporateCustomerResponse result = service.addNewCorporateCustomer(goodCustomer);

        Assert.assertEquals(goodCustomer.getCompanyName(), result.getCompanyName());
        Assert.assertEquals(goodCustomer.getCompanyCode(), result.getCompanyCode());
    }

    @Test
    public void canRetrieveCustomerById() throws Exception{
        when(repository.findCorporateCustomersById(String.valueOf(goodCustomer.getId()))).thenReturn(goodCustomer);

        CorporateCustomerResponse result = service.getCorporateCustomerById(String.valueOf(goodCustomer.getId()));

        Assert.assertEquals(goodCustomer.getCompanyName(), result.getCompanyName());
    }

    @Test(expected = NotFoundException.class)
    public void canRetrieveCustomerByIdShouldThrowNotFoundException() throws Exception{
        when(repository.findCorporateCustomersById(String.valueOf(goodCustomer.getId()))).thenReturn(null);

        CorporateCustomerResponse result = service.getCorporateCustomerById(String.valueOf(goodCustomer.getId()));
    }


    @Test(expected = NotFoundException.class)
    public void canDeleteCustomerByIdShouldThrowNotFoundException() throws Exception{
        when(repository.findCorporateCustomersById(String.valueOf(goodCustomer.getId()))).thenReturn(null);

        service.removeCorporateCustomer(String.valueOf(goodCustomer.getId()));
    }





}