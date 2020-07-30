package zh.learn.spring5.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.services.IngredientService;
import zh.learn.spring5.recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId") Long recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(
            @PathVariable("recipeId") Long recipeId,
            @PathVariable("id") Long id,
            Model model
    ) {
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, id);
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/show";
    }
}
