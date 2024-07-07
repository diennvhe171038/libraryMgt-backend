package swp391.learning.application.service;

import swp391.learning.domain.dto.request.admin.book.BookCopyRequest;

public interface BookCopyService {

    void addBookCopy(BookCopyRequest bookCopyRequest);

    void updateBookCopy(int id, BookCopyRequest bookCopyRequest);

    void deleteBookCopy(int id);


}
