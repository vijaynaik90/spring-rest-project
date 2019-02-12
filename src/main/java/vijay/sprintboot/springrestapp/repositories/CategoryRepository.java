package vijay.sprintboot.springrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vijay.sprintboot.springrestapp.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
