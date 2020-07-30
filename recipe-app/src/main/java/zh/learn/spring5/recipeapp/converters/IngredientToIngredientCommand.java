package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.domain.Ingredient;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null)
            return null;

        IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        if (ingredient.getRecipe() != null)
            command.setRecipeId(ingredient.getRecipe().getId());
        command.setAmount(ingredient.getAmount());
        command.setDescription(ingredient.getDescription());

        UnitOfMeasure uom = ingredient.getUom();
        command.setUom(uomConverter.convert(uom));

        return command;
    }
}
