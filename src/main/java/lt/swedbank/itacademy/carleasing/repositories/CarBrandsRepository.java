package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.CarBrands;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CarBrandsRepository extends CrudRepository<CarBrands, String> {

    List<CarBrands> findAll();
}
