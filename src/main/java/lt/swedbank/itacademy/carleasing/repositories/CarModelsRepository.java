package lt.swedbank.itacademy.carleasing.repositories;

import lt.swedbank.itacademy.carleasing.beans.documents.CarModels;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarModelsRepository extends CrudRepository<CarModels, String> {

    List<CarModels> findAll();
}
