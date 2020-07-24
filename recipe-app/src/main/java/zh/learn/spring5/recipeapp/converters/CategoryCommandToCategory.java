package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.CategoryCommand;
import zh.learn.spring5.recipeapp.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand command) {
        if (command == null)
            return null;

        Category category = new Category();
        category.setId(command.getId());
        category.setDescription(command.getDescription());
        return category;
    }
}
