package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.CorporateCustomer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CorporateCustomerRepository extends CrudRepository<CorporateCustomer, String> {

    List<CorporateCustomer> findAll();

    CorporateCustomer findCorporateCustomersById(String id);

    CorporateCustomer findCorporateCustomersByLeaseId(String id);
}
