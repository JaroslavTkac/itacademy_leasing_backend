package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.carleasing.beans.responses.PrivateCustomerResponse;
import lt.swedbank.itacademy.carleasing.exceptions.NotFoundException;
import lt.swedbank.itacademy.carleasing.repositories.PrivateCustomerRepository;
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

public class PrivateCustomerServiceTest {

    private MockMvc mockMvc;

    @Mock
    private PrivateCustomerRepository repository;

    @InjectMocks
    private PrivateCustomerService service;

    private PrivateCustomer goodCustomer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        goodCustomer = new PrivateCustomer(new ObjectId(), new ObjectId(), "Jaroslav", "Tolvinas",
                "39508201230", "spawn@inbox.lt", "865090090", "Vilniaus g.2");
    }

    @Test
    public void canRetrieveAllCustomers() throws Exception{
        List<PrivateCustomer> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customers.add(goodCustomer);
        }

        when(repository.findAll()).thenReturn(customers);

        List<PrivateCustomerResponse> resultList = service.getAllPrivateCustomers();

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(customers.get(i).getFirstName(), resultList.get(i).getFirstName());
        }
    }

    @Test
    public void canAddNewCustomer() throws Exception{
        when(repository.save(any())).thenReturn(goodCustomer);

        PrivateCustomerResponse result = service.addNewPrivateCustomer(goodCustomer);

        Assert.assertEquals(goodCustomer.getFirstName(), result.getFirstName());
        Assert.assertEquals(goodCustomer.getLastName(), result.getLastName());
    }

    @Test
    public void canRetrieveCustomerById() throws Exception{
        when(repository.findPrivateCustomerById(String.valueOf(goodCustomer.getId()))).thenReturn(goodCustomer);

        PrivateCustomerResponse result = service.getPrivateCustomerById(String.valueOf(goodCustomer.getId()));

        Assert.assertEquals(goodCustomer.getFirstName(), result.getFirstName());
    }


    @Test(expected = NotFoundException.class)
    public void canRetrieveCustomerByIdShouldThrowNotFoundException() throws Exception{
        when(repository.findPrivateCustomerById(String.valueOf(goodCustomer.getId()))).thenReturn(null);

        PrivateCustomerResponse result = service.getPrivateCustomerById(String.valueOf(goodCustomer.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void canDeleteCustomerByIdShouldThrowNotFoundException() throws Exception{
        when(repository.findPrivateCustomerById(String.valueOf(goodCustomer.getId()))).thenReturn(null);

        service.removePrivateCustomer(String.valueOf(goodCustomer.getId()));
    }
}