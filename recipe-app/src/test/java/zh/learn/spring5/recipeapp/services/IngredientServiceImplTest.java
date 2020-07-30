package zh.learn.spring5.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.converters.IngredientToIngredientCommand;
import zh.learn.spring5.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import zh.learn.spring5.recipeapp.domain.Ingredient;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private RecipeRepository recipeRepository;

    private IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIdTest() {
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong()))
                .thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        assertNotNull(ingredientCommand);
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}