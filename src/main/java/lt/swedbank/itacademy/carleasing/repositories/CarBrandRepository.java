package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.CarBrand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CarBrandRepository extends CrudRepository<CarBrand, String> {

    List<CarBrand> findAll();
}
