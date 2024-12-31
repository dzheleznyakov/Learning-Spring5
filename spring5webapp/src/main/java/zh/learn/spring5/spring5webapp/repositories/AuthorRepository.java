package zh.learn.spring5.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.spring5webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
