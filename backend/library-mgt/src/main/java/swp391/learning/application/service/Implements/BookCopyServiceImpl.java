package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.BookCopyService;
import swp391.learning.domain.dto.request.admin.book.BookCopyRequest;
import swp391.learning.domain.entity.BookCopy;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.EnumBookStatus;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;
import swp391.learning.repository.BookCopyRepository;
import swp391.learning.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;
    @Override
    public void addBookCopy(BookCopyRequest bookCopyRequest) {
        log.info("Add book copy");
        BookCopy bookCopy = bookCopyRepository.findByBarcode(bookCopyRequest.getBarcode());
        if (bookCopy != null) {
            log.error("Book copy already exists");
            throw new DuplicateResourceException("Bản sao sách đã tồn tại");
        }

        User user = userRepository.findById(bookCopyRequest.getUserId());
        if (user == null) {
            log.error("User not found");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        BookCopy newBookCopy = new BookCopy();
        newBookCopy.setBarcode(bookCopyRequest.getBarcode());
        newBookCopy.setStatus(EnumBookStatus.valueOf(bookCopyRequest.getStatus()));
        newBookCopy.setCreatedBy(user);
        newBookCopy.setUpdatedBy(user);
        bookCopyRepository.save(newBookCopy);


    }

    @Override
    public void updateBookCopy(int id, BookCopyRequest bookCopyRequest) {

    }

    @Override
    public void deleteBookCopy(int id) {

    }

    @Override
    public BookCopy getBookCopyById(int id) {
        return null;
    }
}
