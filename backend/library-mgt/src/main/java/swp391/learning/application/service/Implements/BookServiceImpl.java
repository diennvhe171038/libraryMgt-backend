package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.application.service.BookService;
import swp391.learning.domain.dto.request.admin.book.BookRequest;
import swp391.learning.domain.entity.*;
import swp391.learning.domain.enums.EnumBookStatus;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.InvalidRequestException;
import swp391.learning.exception.ResourceNotFoundException;
import swp391.learning.repository.AuthorRepository;
import swp391.learning.repository.BookRepository;
import swp391.learning.repository.CategoryRepository;
import swp391.learning.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;


    @Override
    public void addBook(BookRequest addBookRequest) {
        log.info("Adding book with title: {} and userId: {}", addBookRequest.getTitle(), addBookRequest.getUserId());

        User user = userRepository.findById(addBookRequest.getUserId());
        if (user == null) {
            log.info("User with id {} not found", addBookRequest.getUserId());
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        Book existedBook = bookRepository.findByISBN(addBookRequest.getIsbn());
        if (existedBook != null) {
            log.error("Add Book failed: Book is existed");
            throw new DuplicateResourceException("Sách đã tồn tại");
        }

        Book newBook = new Book();
        newBook.setISBN(addBookRequest.getIsbn());
        newBook.setTitle(addBookRequest.getTitle());
        newBook.setDesc(addBookRequest.getDescription());
        newBook.setPrice(addBookRequest.getPrice());
        newBook.setTotalPage(addBookRequest.getTotalPage());
        newBook.setLanguage(addBookRequest.getLanguage());
        newBook.setPublisher(addBookRequest.getPublisher());
        newBook.setPublicationYear(addBookRequest.getPublicationYear());
        newBook.setStatus(EnumBookStatus.AVAILABLE);
        newBook.setUserCreated(user);
        newBook.setUserUpdated(user);

        Set<Author> authors = new HashSet<>();
        for (int authorId : addBookRequest.getAuthorIds()) {
            Author author = authorRepository.findById(authorId);
            if (author == null) {
                log.info("Author with id {} not found", authorId);
                throw new ResourceNotFoundException("Tác giả không tồn tại");
            }
            authors.add(author);
        }
        newBook.setAuthors(authors);

        Set<Category> categories = new HashSet<>();
        for (int categoryId : addBookRequest.getCategoryIds()) {
            Category category = categoryRepository.findCategoryById(categoryId);
            if (category == null) {
                log.info("Category with id {} not found", categoryId);
                throw new ResourceNotFoundException("Danh mục không tồn tại");
            }
            categories.add(category);
        }
        newBook.setCategories(categories);

        String bookImagePath = fileUploadService.saveImage(addBookRequest.getBookImage(), "images");
        newBook.setImagePath(bookImagePath);

        Set<SampleBook> sampleBookImages = new HashSet<>();
        for (MultipartFile sampleBookImage : addBookRequest.getSampleBookImages()) {
            String sampleBookImagePath = fileUploadService.saveImage(sampleBookImage, "sampleImages");
            SampleBook sampleBook = new SampleBook(sampleBookImagePath, newBook);
            sampleBookImages.add(sampleBook);
        }
        newBook.setSampleBooks(sampleBookImages);

        bookRepository.save(newBook);
        log.info("Book {} added successfully", newBook.getTitle());


    }

    @Override
    public void updateBook(int id, BookRequest updateBookRequest) {

    }

    @Override
    public void deleteBook(int id) {

    }

}
