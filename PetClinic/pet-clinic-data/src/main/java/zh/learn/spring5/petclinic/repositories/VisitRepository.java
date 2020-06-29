package zh.learn.spring5.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
