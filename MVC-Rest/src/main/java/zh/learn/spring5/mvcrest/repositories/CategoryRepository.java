package zh.learn.spring5.mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zh.learn.spring5.mvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
