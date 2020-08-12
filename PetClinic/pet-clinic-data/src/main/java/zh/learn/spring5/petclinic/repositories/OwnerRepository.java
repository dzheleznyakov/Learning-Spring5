package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
