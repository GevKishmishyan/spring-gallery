package am.gevorg.springgallery.repository;

import am.gevorg.springgallery.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
