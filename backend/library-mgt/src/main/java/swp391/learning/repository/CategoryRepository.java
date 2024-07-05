package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.learning.domain.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findAllByParentCategoryIsNull();
    Category findCategoryByName(String name);

    Category findCategoryById(int id);
//    List<Category> findAllByIsDeleted(boolean isDeleted);

}
