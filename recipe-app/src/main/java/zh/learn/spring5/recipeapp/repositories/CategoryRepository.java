package zh.learn.spring5.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.recipeapp.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
