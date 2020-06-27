package zh.learn.spring5.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
