package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.RecipeCommand;
import zh.learn.spring5.recipeapp.domain.Notes;
import zh.learn.spring5.recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(
            NotesCommandToNotes notesConverter,
            CategoryCommandToCategory categoryConverter,
            IngredientCommandToIngredient ingredientConverter
    ) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public Recipe convert(RecipeCommand command) {
        if (command == null)
            return null;

        Recipe recipe = new Recipe();
        recipe.setId(command.getId());
        recipe.setCookTime(command.getCookTime());
        recipe.setPrepTime(command.getPrepTime());
        recipe.setDescription(command.getDescription());
        recipe.setDifficulty(command.getDifficulty());
        recipe.setDirections(command.getDirections());
        recipe.setServings(command.getServings());
        recipe.setSource(command.getSource());
        recipe.setUrl(command.getUrl());

        Notes notes = notesConverter.convert(command.getNotes());
        recipe.setNotes(notes);

        if (command.getCategories() != null && !command.getCategories().isEmpty())
            command.getCategories()
                    .forEach(cc -> recipe.getCategories().add(categoryConverter.convert(cc)));

        if (command.getIngredients() != null && !command.getIngredients().isEmpty())
            command.getIngredients()
                    .forEach(ic -> recipe.getIngredients().add(ingredientConverter.convert(ic)));
        return recipe;
    }
}
