package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.learning.domain.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

        Book findById(int id);

        Book findByISBN(String isbn);

//    @Query("SELECT b FROM Book b " +
//            "JOIN b.category c " +
//            "WHERE LOWER(b.nameBook) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(c.nameCategory) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//        List<Book> searchBookByNameOrCategory(@Param("keyword") String keyword);


    }

