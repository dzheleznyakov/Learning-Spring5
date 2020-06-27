package zh.learn.spring5.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
