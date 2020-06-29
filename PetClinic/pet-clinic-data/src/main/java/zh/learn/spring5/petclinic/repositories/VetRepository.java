package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
