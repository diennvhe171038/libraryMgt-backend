package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.learning.domain.entity.BookCopy;
import swp391.learning.domain.enums.EnumBookStatus;

import java.util.List;
import java.util.Set;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {

    BookCopy findByBarcode(String barcode);

    BookCopy findById(int id);

    Set<BookCopy> findByBookId(int bookId);

    int countByStatusAndBookId(EnumBookStatus status, int bookId);
}