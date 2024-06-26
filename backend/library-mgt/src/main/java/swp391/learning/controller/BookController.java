package swp391.learning.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.application.service.BookService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.book.AddBookRequest;
import swp391.learning.domain.dto.request.admin.book.DeleteBookRequest;
import swp391.learning.domain.dto.request.admin.book.UpdateBookRequest;
import swp391.learning.domain.dto.request.user.book.SearchBookByNameAndCategoryRequest;
import swp391.learning.domain.dto.response.admin.book.AddBookResponse;
import swp391.learning.domain.dto.response.admin.book.DeleteBookResponse;
import swp391.learning.domain.dto.response.user.book.SearchBookByNameAndCategoryResponse;
import swp391.learning.domain.dto.response.admin.book.UpdateBookResponse;
import swp391.learning.domain.enums.ResponseCode;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;
//    private final int TOP_BOOK = 10;

    @PostMapping("/add-book")
    public ResponseEntity<ResponseCommon<AddBookResponse>> addBook(
            @Valid @RequestBody AddBookRequest addBookRequest,
            @RequestParam("image") MultipartFile file){
        ResponseCommon<AddBookResponse> response = bookService.addBook(addBookRequest, file);
        if(response.getCode() == ResponseCode.SUCCESS.getCode()){
            return ResponseEntity.ok(response);
        } else if(response.getCode() == ResponseCode.BOOK_EXIST.getCode()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseCommon<>(response.getCode(),"Book already exists",null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Add book fail",null));
        }
    }

    @PostMapping("/update-book")
    public ResponseEntity<ResponseCommon<UpdateBookResponse>> updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest,
                                                                         @RequestParam("image") MultipartFile file){
        ResponseCommon<UpdateBookResponse> response = bookService.updateBook(updateBookRequest,file);
        // if code of response equal code success -> return ok
        if(response.getCode()==ResponseCode.SUCCESS.getCode()){
            return ResponseEntity.ok(response);
        } // if code equal Book not exist -> tell error
        else if (response.getCode()==ResponseCode.BOOK_NOT_EXIST.getCode()) {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(response.getCode(),"Book not exist",null));
        } // else -> return fail  update
        else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Update book fail",null));
        }
    }

    @PostMapping("/delete-book")
    public ResponseEntity<ResponseCommon<DeleteBookResponse>> deleteBook(@Valid @RequestBody DeleteBookRequest deleteBookRequest){
        ResponseCommon<DeleteBookResponse> response = bookService.deleteBook(deleteBookRequest);
        // if code of response equal code success -> return ok
        if(response.getCode()==ResponseCode.SUCCESS.getCode()){
            return ResponseEntity.ok(response);
        } // if code equal Book not exist -> tell error
        else if (response.getCode()==ResponseCode.BOOK_NOT_EXIST.getCode()) {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(response.getCode(),"Book not exist",null));
        } // else -> return fail  update
        else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Delete Book fail",null));
        }
    }
    @GetMapping("/search-book")
    public ResponseEntity<ResponseCommon<SearchBookByNameAndCategoryResponse>> searchBook(SearchBookByNameAndCategoryRequest searchBookByNameAndCategoryRequest){
        ResponseCommon<SearchBookByNameAndCategoryResponse> response = bookService.searchBook(searchBookByNameAndCategoryRequest);
        // if response code quals empty list code -> tell user
        if(response.getCode() == ResponseCode.BOOK_LIST_IS_EMPTY.getCode()){
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.BOOK_LIST_IS_EMPTY.getCode(),"Book list is empty",null));
        } else if(response.getCode() == ResponseCode.SUCCESS.getCode()){
            return ResponseEntity.ok().body(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(),"Search book success",response.getData()));
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Search book fail",null));
        }
    }

}
