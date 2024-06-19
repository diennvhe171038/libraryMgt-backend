package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.learning.domain.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findCategoryByNameCategory(String nameCategory);

    Optional<Category> findCategoryById(int id);
//    List<Category> findAllByIsDeleted(boolean isDeleted);

}
