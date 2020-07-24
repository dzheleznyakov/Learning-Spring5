package zh.learn.spring5.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.UnitOfMeasureCommand;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand command) {
        if (command == null)
            return null;

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(command.getId());
        uom.setDescription(command.getDescription());
        return uom;
    }
}
