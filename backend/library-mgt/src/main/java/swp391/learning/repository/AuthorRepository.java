package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.learning.domain.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findAuthorByNameAuthor(String name);

    Optional<Author> findAuthorById(int id);
}
