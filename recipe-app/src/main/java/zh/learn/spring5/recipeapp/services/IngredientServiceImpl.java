package zh.learn.spring5.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.converters.IngredientToIngredientCommand;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.repositories.RecipeRepository;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(
            IngredientToIngredientCommand ingredientToIngredientCommand,
            RecipeRepository recipeRepository
    ) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            log.error(String.format("Recipe not found. Id=[%d]", recipeId));
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> Objects.equals(ingredient.getId(), ingredientId))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

        if (ingredientCommandOptional.isEmpty()) {
            log.error(String.format("Ingredient not found. Id=[%d]", ingredientId));
        }

        return ingredientCommandOptional.get();
    }
}
