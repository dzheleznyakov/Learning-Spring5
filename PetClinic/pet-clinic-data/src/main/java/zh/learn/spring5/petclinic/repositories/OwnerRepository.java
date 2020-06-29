package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
