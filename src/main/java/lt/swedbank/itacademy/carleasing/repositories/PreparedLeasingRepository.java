package lt.swedbank.itacademy.carleasing.repositories;

import org.springframework.data.repository.CrudRepository;
import lt.swedbank.itacademy.carleasing.beans.documents.PreparedLeasing;

public interface PreparedLeasingRepository extends CrudRepository<PreparedLeasing, String> {
}
