package swp391.learning.application.service;

import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.book.AddBookRequest;
import swp391.learning.domain.dto.request.admin.book.DeleteBookRequest;
import swp391.learning.domain.dto.request.admin.book.UpdateBookRequest;
import swp391.learning.domain.dto.request.user.book.SearchBookByNameAndCategoryRequest;
import swp391.learning.domain.dto.response.admin.book.AddBookResponse;
import swp391.learning.domain.dto.response.admin.book.DeleteBookResponse;
import swp391.learning.domain.dto.response.admin.book.UpdateBookResponse;
import swp391.learning.domain.dto.response.user.book.SearchBookByNameAndCategoryResponse;

public interface BookService {
    ResponseCommon<AddBookResponse> addBook(AddBookRequest addBookRequest);

    ResponseCommon<UpdateBookResponse> updateBook(UpdateBookRequest updateBookRequest);

    ResponseCommon<DeleteBookResponse> deleteBook(DeleteBookRequest deleteBookRequest);

    ResponseCommon<SearchBookByNameAndCategoryResponse> searchBook(SearchBookByNameAndCategoryRequest searchBookByNameAndCategoryRequest);
}
