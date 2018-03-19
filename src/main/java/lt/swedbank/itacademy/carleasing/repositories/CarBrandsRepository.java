package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.CarBrands;
import org.springframework.data.repository.CrudRepository;


public interface CarBrandsRepository extends CrudRepository<CarBrands, String> {
}
