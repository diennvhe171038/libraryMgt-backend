package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.BookCopyService;
import swp391.learning.domain.dto.request.admin.book.BookCopyRequest;
import swp391.learning.domain.entity.Book;
import swp391.learning.domain.entity.BookCopy;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.EnumBookStatus;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;
import swp391.learning.repository.BookCopyRepository;
import swp391.learning.repository.BookRepository;
import swp391.learning.repository.UserRepository;
@Service
@Slf4j
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @Override
    public void addBookCopy(BookCopyRequest bookCopyRequest) {
        log.info("Add book copy");
        BookCopy bookCopy = bookCopyRepository.findByBarcode(bookCopyRequest.getBarcode());
        if (bookCopy != null) {
            log.error("Book copy already exists");
            throw new DuplicateResourceException("Bản sao sách đã tồn tại");
        }

        Book book = bookRepository.findById(bookCopyRequest.getBookId());
        if (book == null) {
            log.error("Book not found");
            throw new ResourceNotFoundException("Sách không tồn tại");
        }

        User user = userRepository.findById(bookCopyRequest.getUserId());
        if (user == null) {
            log.error("User not found");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        BookCopy newBookCopy = new BookCopy();
        newBookCopy.setBook(book);
        newBookCopy.setBarcode(bookCopyRequest.getBarcode());
        newBookCopy.setStatus(EnumBookStatus.valueOf(bookCopyRequest.getStatus()));
        newBookCopy.setCreatedBy(user);
        newBookCopy.setUpdatedBy(user);
        bookCopyRepository.save(newBookCopy);
        log.info("Add book copy successfully");

    }

    @Override
    public void updateBookCopy(int id, BookCopyRequest bookCopyRequest) {
        log.info("Update book copy");

        BookCopy bookCopy = bookCopyRepository.findById(id);
        if (bookCopy == null) {
            log.error("Book copy not found");
            throw new ResourceNotFoundException("Bản sao sách không tồn tại");
        }

        Book book = bookRepository.findById(bookCopyRequest.getBookId());
        if (book == null) {
            log.error("Book not found");
            throw new ResourceNotFoundException("Sách không tồn tại");
        }

        User user = userRepository.findById(bookCopyRequest.getUserId());
        if (user == null) {
            log.error("User not found");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        bookCopy.setBook(book);
        bookCopy.setBarcode(bookCopyRequest.getBarcode());
        bookCopy.setStatus(EnumBookStatus.valueOf(bookCopyRequest.getStatus()));
        bookCopy.setUpdatedBy(user);
        bookCopyRepository.save(bookCopy);
        log.info("Update book copy successfully");
    }

    @Override
    public void deleteBookCopy(int id) {
        log.info("Delete book copy");
        BookCopy bookCopy = bookCopyRepository.findById(id);
        if (bookCopy == null) {
            log.error("Book copy not found");
            throw new ResourceNotFoundException("Bản sao sách không tồn tại");
        }
        bookCopyRepository.delete(bookCopy);
        log.info("Delete book copy successfully");

    }




}
