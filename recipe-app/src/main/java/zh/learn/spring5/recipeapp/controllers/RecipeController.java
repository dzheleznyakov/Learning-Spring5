package zh.learn.spring5.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import zh.learn.spring5.recipeapp.commands.RecipeCommand;
import zh.learn.spring5.recipeapp.exceptions.NotFoundException;
import zh.learn.spring5.recipeapp.services.RecipeService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private static final String RECIPE_FORM_VIEW = "recipe/recipeform";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return RECIPE_FORM_VIEW;
    }

    @PostMapping({"/", ""})
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_FORM_VIEW;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return String.format("redirect:/recipe/%s/show", savedCommand.getId());
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        log.debug(String.format("Delete id: [%s]", id));
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception", exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
