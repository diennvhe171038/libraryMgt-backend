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
import swp391.learning.domain.entity.Book;
import swp391.learning.domain.entity.Category;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.repository.BookRepository;
import swp391.learning.repository.CategoryRepository;
import swp391.learning.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
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
            // if Book is null -> new Book
            if (Objects.isNull(book)) {
                book = new Book();
            }
            book.setNameBook(addBookRequest.getName());
            book.setDesc(addBookRequest.getDescription());
            book.setPrice(addBookRequest.getPrice());
            book.setCreatedAt(LocalDateTime.now());
            Category category = categoryRepository.findCategoryByNameCategory(addBookRequest.getCategory()).orElse(null);
            System.out.println(category);
            book.setCategory(category);
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
            Book bookExist = bookRepository.findBookByNameBook(updateBookRequest.getName()).orElse(null);
            User user = userRepository.findByEmail(updateBookRequest.getEmail());
            // if bookExist is null -> tell the user
            if (Objects.isNull(bookExist)) {
                log.debug("Update Book failed: Book does not exist");
                return new ResponseCommon<>(ResponseCode.BOOK_NOT_EXIST, null);
            } else {
                Category category = categoryRepository.findCategoryById(updateBookRequest.getCategoryID()).orElse(null);
                Book bookUpdate = bookExist;
                bookUpdate.setCategory(category);
                bookUpdate.setNameBook(updateBookRequest.getName());
                bookUpdate.setDesc(updateBookRequest.getDescription());
                bookUpdate.setPrice(updateBookRequest.getPrice());
                bookUpdate.setCreatedAt(LocalDateTime.now());
                bookUpdate.setUpdatedAt(LocalDateTime.now());
                bookUpdate.setDeleted(updateBookRequest.isDeleted());
                bookUpdate.setUserUpdated(user);
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
    public ResponseCommon<DeleteBookResponse> deleteBook(DeleteBookRequest deleteBookRequest, MultipartFile file) {
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
}
