package zh.learn.spring5.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import zh.learn.spring5.recipeapp.commands.IngredientCommand;
import zh.learn.spring5.recipeapp.domain.Ingredient;
import zh.learn.spring5.recipeapp.domain.Recipe;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long ID_VALUE = 1L;
    private static final Long UOM_ID = 2L;

    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObjectConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjectConvert() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertWithNullUom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount() );
        assertEquals(DESCRIPTION, command.getDescription());
    }

    @Test
    public void testConvertWithUom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
        assertNotNull(command.getUom());
        assertEquals(UOM_ID, command.getUom().getId());
    }
}
