package zh.learn.spring5.webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.webapp.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
