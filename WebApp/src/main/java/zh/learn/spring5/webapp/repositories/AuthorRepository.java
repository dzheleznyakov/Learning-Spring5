package zh.learn.spring5.webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
