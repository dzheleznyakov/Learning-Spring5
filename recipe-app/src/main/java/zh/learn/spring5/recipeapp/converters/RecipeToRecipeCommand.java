package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.NotesCommand;
import zh.learn.spring5.recipeapp.commands.RecipeCommand;
import zh.learn.spring5.recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null)
            return null;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());

        NotesCommand notesCommand = notesConverter.convert(recipe.getNotes());
        recipeCommand.setNotes(notesCommand);

        if (recipe.getCategories() != null && !recipe.getCategories().isEmpty())
            recipe.getCategories().stream()
                    .map(categoryConverter::convert)
                    .forEach(recipeCommand.getCategories()::add);

        if (recipe.getIngredients() != null && !recipe.getIngredients().isEmpty())
            recipe.getIngredients().stream()
                    .map(ingredientConverter::convert)
                    .forEach(recipeCommand.getIngredients()::add);

        return recipeCommand;
    }
}
