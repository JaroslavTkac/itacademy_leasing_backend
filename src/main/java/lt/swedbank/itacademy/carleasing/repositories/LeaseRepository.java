package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaseRepository extends CrudRepository<Lease, String> {

    List<Lease> findAll();

    Lease findLeasingById(String id);
}
