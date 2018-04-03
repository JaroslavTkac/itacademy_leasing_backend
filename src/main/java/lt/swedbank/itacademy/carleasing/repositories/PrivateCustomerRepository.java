package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.PrivateCustomer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrivateCustomerRepository extends CrudRepository<PrivateCustomer, String> {

    List<PrivateCustomer> findAll();

    PrivateCustomer findPrivateCustomerById(String id);
}
