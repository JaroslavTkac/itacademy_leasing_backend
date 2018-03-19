package lt.swedbank.itacademy.carleasing.repositories;

import org.springframework.data.repository.CrudRepository;
import lt.swedbank.itacademy.carleasing.beans.documents.User;

public interface UserRepository extends CrudRepository<User, String> {
}
