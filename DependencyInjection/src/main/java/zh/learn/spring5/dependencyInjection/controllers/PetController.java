package zh.learn.spring5.dependencyInjection.controllers;

import org.springframework.stereotype.Controller;
import zh.learn.spring5.dependencyInjection.services.PetService;

@Controller
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public String whichPetIsTheBest(){
        return petService.getPetType();
    }
}
