package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
