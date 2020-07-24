package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.UnitOfMeasureCommand;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure uom) {
        if (uom == null)
            return null;

        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(uom.getId());
        command.setDescription(uom.getDescription());
        return command;
    }
}
