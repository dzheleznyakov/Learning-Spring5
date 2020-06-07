package zh.learn.spring5.webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring5.webapp.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
