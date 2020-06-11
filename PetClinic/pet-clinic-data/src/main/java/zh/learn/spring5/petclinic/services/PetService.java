package zh.learn.spring5.petclinic.services;

import zh.learn.spring5.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
