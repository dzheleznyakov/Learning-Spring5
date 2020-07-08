package zh.learn.spring5.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zh.learn.spring5.petclinic.model.Pet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PetMapServiceTest {
    PetMapService petMapService;

    final Long petId = 1L;
    final Pet pet = Pet.builder()
            .id(petId)
            .build();

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet foundPet = petMapService.findById(petId);

        assertEquals(petId, foundPet.getId());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        assertTrue(petMapService.findAll().isEmpty());
    }

    @Test
    void delete() {
        petMapService.delete(pet);

        assertTrue(petMapService.findAll().isEmpty());
    }

    @Test
    void save() {
        assertEquals(1, petMapService.findAll().size());

        Long secondPetId = 2L;
        Pet secondPet = Pet.builder()
                .id(secondPetId)
                .build();

        Pet savedPet = petMapService.save(secondPet);

        assertNotNull(savedPet);
        assertEquals(secondPetId, savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }
}