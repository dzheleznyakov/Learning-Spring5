package zh.learn.spring5.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
