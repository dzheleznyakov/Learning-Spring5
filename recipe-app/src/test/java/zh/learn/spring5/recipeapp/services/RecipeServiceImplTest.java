package zh.learn.spring5.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zh.learn.spring5.recipeapp.commands.RecipeCommand;
import zh.learn.spring5.recipeapp.converters.RecipeCommandToRecipe;
import zh.learn.spring5.recipeapp.converters.RecipeToRecipeCommand;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.exceptions.NotFoundException;
import zh.learn.spring5.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong()))
                .thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(1L);

        assertNotNull(returnedRecipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong()))
                .thenReturn(recipeOptional);

        recipeService.findById(1L);
    }

    @Test
    public void getRecipeCommandById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong()))
                .thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any()))
                .thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull(commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll())
                .thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void deleteById() {
        Long idToDelete = 2L;
        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}