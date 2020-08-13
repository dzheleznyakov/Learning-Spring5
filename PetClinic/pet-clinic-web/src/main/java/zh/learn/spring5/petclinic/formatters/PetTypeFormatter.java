package zh.learn.spring5.petclinic.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import zh.learn.spring5.petclinic.model.PetType;
import zh.learn.spring5.petclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String petTypeName, Locale locale) throws ParseException {
        for (PetType petType : petTypeService.findAll()) {
            if (Objects.equals(petType.getName(), petTypeName))
                return petType;
        }
        throw new ParseException("Pet type not found: " + petTypeName, 0);
    }
}
