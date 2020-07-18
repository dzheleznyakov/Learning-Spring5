package zh.learn.spring5.recipeapp.services;

import zh.learn.spring5.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
