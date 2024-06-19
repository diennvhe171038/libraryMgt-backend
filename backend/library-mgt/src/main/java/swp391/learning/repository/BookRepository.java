package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391.learning.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
        Optional<Book> findBookByNameBook(String bookName);

        Optional<Book> findBookById(int id);

        @Query("SELECT c FROM Book c " +
                "WHERE LOWER(c.nameBook) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                "OR LOWER(c.category.nameCategory) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<Book> searchBookByNameOrCategory(@Param("keyword") String keyword);


    }

