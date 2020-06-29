package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
