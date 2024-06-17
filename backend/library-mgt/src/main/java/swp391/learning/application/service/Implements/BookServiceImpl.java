package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
import swp391.learning.repository.AuthenticationRepository;
import swp391.learning.repository.BookRepository;
import swp391.learning.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);


    @Override
    public ResponseCommon<AddBookResponse> addBook(AddBookRequest addBookRequest) {
        try {
            Book book = bookRepository.findBookByName(addBookRequest.getName()).orElse(null);
            User user = authenticationRepository.findByUsername(addBookRequest.getUsername()).orElse(null);
            // if Book not null -> tell the user
            if (!Objects.isNull(book)) {
                log.debug("Add Book failed: Book already exists");
                return new ResponseCommon<>(ResponseCode.BOOK_EXIST, null);
            }
            // if Book is null -> new Book
            if (Objects.isNull(book)) {
                book = new Book();
            }
            book.setName(addBookRequest.getName());
            book.setDescription(addBookRequest.getDescription());
            book.setLinkThumnail(addBookRequest.getLink_thumnail());
            book.setPrice(addBookRequest.getPrice());
            book.setCreatedAt(LocalDateTime.now());
            Category category = categoryRepository.findCategoryByName(addBookRequest.getCategory()).orElse(null);
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
            addBookResponse.setBookID(book.getId());
            addBookResponse.setBookName(book.getName());
            addBookResponse.setDescription(book.getDescription());
            addBookResponse.setPrice(book.getPrice());
            addBookResponse.setCategory(book.getCategory());
            addBookResponse.setLinkThumail(book.getLinkThumnail());
            addBookResponse.setCreatedAt(book.getCreatedAt());
            addBookResponse.setCreatedBy(user.getUsername());
            log.debug("Add Book successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, addBookResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Add Book failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<UpdateBookResponse> updateBook(UpdateBookRequest updateBookRequest) {
        try {
            Book bookExist = bookRepository.findBookByName(updateBookRequest.getName()).orElse(null);
            User user = authenticationRepository.findByUsername(updateBookRequest.getUsername()).orElse(null);
            // if bookExist is null -> tell the user
            if (Objects.isNull(bookExist)) {
                log.debug("Update Book failed: Book does not exist");
                return new ResponseCommon<>(ResponseCode.BOOK_NOT_EXIST, null);
            } else {
                Category category = categoryRepository.findCategoryById(updateBookRequest.getCategoryID()).orElse(null);
                Book bookUpdate = bookExist;
                bookUpdate.setCategory(category);
                bookUpdate.setName(updateBookRequest.getName());
                bookUpdate.setDescription(updateBookRequest.getDescription());
                bookUpdate.setPrice(updateBookRequest.getPrice());
                bookUpdate.setLinkThumnail(updateBookRequest.getLink_thumnail());
                bookUpdate.setCreatedAt(LocalDateTime.now());
                bookUpdate.setUpdatedAt(LocalDateTime.now());
                bookUpdate.setDeleted(updateBookRequest.isDeleted());
                bookUpdate.setUserUpdated(user);
                bookRepository.save(bookUpdate);
                UpdateBookResponse updateBookResponse = new UpdateBookResponse();
                updateBookResponse.setBookID(bookUpdate.getId());
                updateBookResponse.setBookName(bookUpdate.getName());
                updateBookResponse.setDescription(bookUpdate.getDescription());
                updateBookResponse.setPrice(bookUpdate.getPrice());
                updateBookResponse.setCategory(bookUpdate.getCategory());
                updateBookResponse.setLinkThumail(bookUpdate.getLinkThumnail());
                LocalDateTime localDateTime = LocalDateTime.now();
                updateBookResponse.setUpdateAt(localDateTime);
                updateBookResponse.setCreateAt(bookUpdate.getCreatedAt());
                updateBookResponse.setUpdatedBy(bookUpdate.getUserUpdated().getUsername());
                updateBookResponse.setCreatedBy(bookUpdate.getUserCreated().getUsername());
                log.debug("Update Book successful");
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
            Book bookExist = bookRepository.findBookByName(deleteBookRequest.getUsername()).orElse(null);
            User user = authenticationRepository.findByUsername(deleteBookRequest.getUsername()).orElse(null);
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
                deleteBookResponse.setBookID(bookDelete.getId());
                deleteBookResponse.setBookName(bookDelete.getName());
                deleteBookResponse.setDescription(bookDelete.getDescription());
                deleteBookResponse.setPrice(bookDelete.getPrice());
                deleteBookResponse.setCategory(bookDelete.getCategory());
                deleteBookResponse.setLinkThumail(bookDelete.getLinkThumnail());
                deleteBookResponse.setCreatedAt(bookDelete.getCreatedAt());
                deleteBookResponse.setDeleted(bookDelete.isDeleted());
                deleteBookResponse.setCreatedBy(bookDelete.getUserCreated().getUsername());
                deleteBookResponse.setUpdatedBy(bookDelete.getUserUpdated().getUsername());
                log.debug("Delete Book successful");
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
