package zh.learn.spring5.recipeapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    private IndexController indexController;
    private Set<Recipe> recipesData;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);

        recipesData = new HashSet<>();
        recipesData.add(new Recipe());

        when(recipeService.getRecipes())
                .thenReturn(recipesData);
    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = indexController.getIndexPage(model);

        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1))
                .addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(recipesData, setInController);
    }
}