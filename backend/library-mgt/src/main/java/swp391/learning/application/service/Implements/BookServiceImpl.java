package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.application.service.BookService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.book.AddBookRequest;
import swp391.learning.domain.dto.request.admin.book.DeleteBookRequest;
import swp391.learning.domain.dto.request.admin.book.UpdateBookRequest;
import swp391.learning.domain.dto.request.user.book.SearchBookByNameAndCategoryRequest;
import swp391.learning.domain.dto.response.admin.book.AddBookResponse;
import swp391.learning.domain.dto.response.admin.book.DeleteBookResponse;
import swp391.learning.domain.dto.response.admin.book.UpdateBookResponse;
import swp391.learning.domain.dto.response.user.book.SearchBookByNameAndCategoryResponse;
import swp391.learning.domain.entity.Author;
import swp391.learning.domain.entity.Book;
import swp391.learning.domain.entity.Category;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.ResponseCode;
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

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private  final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);


    @Override
    public ResponseCommon<AddBookResponse> addBook(AddBookRequest addBookRequest, MultipartFile file) {
        try {
            Book book = bookRepository.findBookByNameBook(addBookRequest.getName()).orElse(null);
            User user = userRepository.findByEmail(addBookRequest.getEmail());
            // if Book not null -> tell the user
            if (!Objects.isNull(book)) {
                log.debug("Add Book failed: Book already exists");
                return new ResponseCommon<>(ResponseCode.BOOK_EXIST, null);
            }
            String filePath = saveFile(file);

            book = new Book();
            book.setNameBook(addBookRequest.getName());
            book.setDesc(addBookRequest.getDescription());
            book.setPrice(addBookRequest.getPrice());
            book.setISBN(addBookRequest.getISBN());
            book.setLanguage(addBookRequest.getLanguage());
            book.setCreatedAt(LocalDateTime.now());
            book.setImagePath(filePath);
            book.setPrice(addBookRequest.getPrice());
            book.setPublisher(addBookRequest.getPublisher());
            book.setPublicationYear(addBookRequest.getPublicationYear());
            book.setStock(addBookRequest.getStock());
            Author author = authorRepository.findAuthorByName(addBookRequest.getAuthor().getName());
            if (author == null) {
                log.debug("Add Book failed: Category does not exist");
                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
            }
            book.setAuthors(Set.of(author));
            Category category = categoryRepository.findCategoryByName(addBookRequest.getCategory().getName());
            if (category == null) {
                log.debug("Add Book failed: Category does not exist");
                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
            }
            book.setCategory(Set.of(category));
            book.setUserCreated(user);

            // Save Book to the database
            Book savedBook = bookRepository.save(book);

            // If the Book is not saved successfully, return a FAIL response
            if (savedBook == null) {
                log.debug("Add Book failed: Unable to save the Book");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }

            AddBookResponse addBookResponse = new AddBookResponse();
            addBookResponse.setMessage("Add Book successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, addBookResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Add Book failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<UpdateBookResponse> updateBook(UpdateBookRequest updateBookRequest, MultipartFile file) {
        try {
            Book bookExist = bookRepository.findBookByNameBook(updateBookRequest.getNameBook()).orElse(null);
            User user = userRepository.findByEmail(updateBookRequest.getEmail());
            // if bookExist is null -> tell the user
            if (Objects.isNull(bookExist)) {
                log.debug("Update Book: Book does not exist");
                return new ResponseCommon<>(ResponseCode.BOOK_NOT_EXIST, null);
            } else {
                Category category = categoryRepository.findCategoryByName(updateBookRequest.getCategory().getName());
                Book bookUpdate = bookExist;
                bookUpdate.setCategory(Set.of(category));
                bookUpdate.setNameBook(updateBookRequest.getNameBook());
                bookUpdate.setDesc(updateBookRequest.getDescription());
                bookUpdate.setPrice(updateBookRequest.getPrice());
                bookUpdate.setISBN(updateBookRequest.getISBN());
                bookUpdate.setStock(updateBookRequest.getStock());
                bookUpdate.setCreatedAt(LocalDateTime.now());
                bookUpdate.setUpdatedAt(LocalDateTime.now());
                bookUpdate.setDeleted(updateBookRequest.isDeleted());
                bookUpdate.setTotalPage(updateBookRequest.getTotalPage());
                bookUpdate.setLanguage(updateBookRequest.getLanguage());
                bookUpdate.setPublisher(updateBookRequest.getPublisher());
                bookUpdate.setPublicationYear(updateBookRequest.getPublicationYear());
                Author author = authorRepository.findAuthorByName(updateBookRequest.getAuthor().getName());
                if (author == null) {
                    log.debug("Update Book: Author does not exist");
                    return new ResponseCommon<>(ResponseCode.AUTHOR_NOT_EXIST, null);
                }
                bookUpdate.setUserUpdated(user);
                if (file != null && !file.isEmpty()) {
                    String filePath = saveFile(file);
                    bookExist.setImagePath(filePath);
                }

                bookRepository.save(bookUpdate);
                UpdateBookResponse updateBookResponse = new UpdateBookResponse();
                updateBookResponse.setMessage("Update Book successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, updateBookResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Update Book failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<DeleteBookResponse> deleteBook(DeleteBookRequest deleteBookRequest) {
        try {
            Book bookExist = bookRepository.findBookByNameBook(deleteBookRequest.getNameBook()).orElse(null);
            User user = userRepository.findByEmail(deleteBookRequest.getEmail());
            // if bookExist is null -> tell the user
            if (Objects.isNull(bookExist)) {
                log.debug("Delete Book failed: Book does not exist");
                return new ResponseCommon<>(ResponseCode.BOOK_NOT_EXIST, null);
            } else {
                Book bookDelete = bookExist;
                bookDelete.setDeleted(true);
                bookDelete.setUpdatedAt(LocalDateTime.now());
                bookDelete.setUserUpdated(user);
                bookRepository.save(bookDelete);
                DeleteBookResponse deleteBookResponse = new DeleteBookResponse();
                deleteBookResponse.setMessage("Delete Book successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, deleteBookResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Delete Book failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<SearchBookByNameAndCategoryResponse> searchBook(SearchBookByNameAndCategoryRequest searchBookByNameAndCategoryRequest) {
        try {
            List<Book> bookList = bookRepository.searchBookByNameOrCategory(searchBookByNameAndCategoryRequest.getKeyword());
            // if bookList is empty
            if (bookList.isEmpty()) {
                return new ResponseCommon<>(ResponseCode.BOOK_LIST_IS_EMPTY.getCode(), "Not book match with search", null);
            } else {
                SearchBookByNameAndCategoryResponse searchBookByNameAndCategoryResponse = new SearchBookByNameAndCategoryResponse(bookList);
                return new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Search success", searchBookByNameAndCategoryResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Get search Book failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
    private String saveFile(MultipartFile file) throws Exception {
        String uploadDir = "uploads/";
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        File serverFile = new File(filePath);
        file.transferTo(serverFile);
        return filePath;
    }
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
