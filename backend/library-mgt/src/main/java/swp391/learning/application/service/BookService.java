package swp391.learning.application.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.domain.dto.common.PageResponse;
import swp391.learning.domain.dto.common.ResponseSuccess;
import swp391.learning.domain.dto.request.admin.book.BookRequest;
import swp391.learning.domain.dto.response.admin.book.BookResponse;
import swp391.learning.domain.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    Book addBook(BookRequest addBookRequest);

    Book updateBook(int id, BookRequest updateBookRequest);

    void deleteBook(int id);

    void uploadBookImage(int id, MultipartFile file);

    void uploadSampleBookImages(int id, Set<MultipartFile> files);

    PageResponse<?> getBooks(int pageNo, int pageSize, String search, Integer categoryId);

    BookResponse getBookById(int id);

    Resource getBookImage(int id);
    Resource getSampleBookImages(int id);

}
