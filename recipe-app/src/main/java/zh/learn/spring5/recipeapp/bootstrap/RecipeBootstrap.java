package zh.learn.spring5.recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zh.learn.spring5.recipeapp.domain.Category;
import zh.learn.spring5.recipeapp.domain.Difficulty;
import zh.learn.spring5.recipeapp.domain.Ingredient;
import zh.learn.spring5.recipeapp.domain.Notes;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;
import zh.learn.spring5.recipeapp.repositories.CategoryRepository;
import zh.learn.spring5.recipeapp.repositories.RecipeRepository;
import zh.learn.spring5.recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(
            CategoryRepository categoryRepository,
            RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        log.debug("Bootstrapping recipes...");
        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each").orElseThrow(() -> new RuntimeException("Expected UOM not found"));
        UnitOfMeasure tablespoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").orElseThrow(() -> new RuntimeException("Expected UOM not found"));
        UnitOfMeasure teaspoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(() -> new RuntimeException("Expected UOM not found"));
        UnitOfMeasure dashUom = unitOfMeasureRepository.findByDescription("Dash").orElseThrow(() -> new RuntimeException("Expected UOM not found"));
        UnitOfMeasure pintUom = unitOfMeasureRepository.findByDescription("Pint").orElseThrow(() -> new RuntimeException("Expected UOM not found"));
        UnitOfMeasure cupUom = unitOfMeasureRepository.findByDescription("Cup").orElseThrow(() -> new RuntimeException("Expected UOM not found"));

        Category americanCategory = categoryRepository.findByDescription("American").orElseThrow(() -> new RuntimeException("Expected Category not found"));
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").orElseThrow(() -> new RuntimeException("Expected Category not found"));

        log.debug("Bootstrapping 'Perfect Guacamole'...");
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(5), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chilies, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        log.debug("Bootstrapping 'Spicy Grilled Chicken Taco'...");
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setServings(4);
        tacoRecipe.setSource("Simply Recipes");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula (3 ounces)", new BigDecimal(3), cupUom));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        tacoRecipe.addIngredient(new Ingredient("roughly chopped cilantro", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUom));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

        log.debug("Recipes bootstrapped");
        return recipes;
    }
}
