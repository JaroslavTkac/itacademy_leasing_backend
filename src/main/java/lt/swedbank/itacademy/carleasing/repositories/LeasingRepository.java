package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.Leasing;
import org.springframework.data.repository.CrudRepository;

public interface LeasingRepository extends CrudRepository<Leasing, String> {
}
