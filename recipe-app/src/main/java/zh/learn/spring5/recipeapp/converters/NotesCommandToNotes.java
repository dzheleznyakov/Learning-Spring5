package zh.learn.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.recipeapp.commands.NotesCommand;
import zh.learn.spring5.recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand command) {
        if (command == null)
            return null;

        Notes notes = new Notes();
        notes.setId(command.getId());
        notes.setRecipeNotes(command.getRecipeNotes());
        return notes;
    }
}
