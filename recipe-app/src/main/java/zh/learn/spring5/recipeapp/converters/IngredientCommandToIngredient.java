package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.domain.Ingredient;
import zh.learn.spring5.recipeapp.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand command) {
        if (command == null)
            return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(command.getId());

        if (command.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(command.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(command.getAmount());
        ingredient.setDescription(command.getDescription());
        ingredient.setUom(uomConverter.convert(command.getUom()));
        return ingredient;
    }
}
