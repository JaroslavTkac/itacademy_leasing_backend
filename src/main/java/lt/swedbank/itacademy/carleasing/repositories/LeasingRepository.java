package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.Leasing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeasingRepository extends CrudRepository<Leasing, String> {

    List<Leasing> findAll();

    Leasing findLeasingById(String id);
}
