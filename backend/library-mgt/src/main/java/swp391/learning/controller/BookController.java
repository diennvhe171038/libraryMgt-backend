package swp391.learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.application.service.BookService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.common.ResponseError;
import swp391.learning.domain.dto.common.ResponseSuccess;
import swp391.learning.domain.dto.request.admin.book.AddBookRequest;
import swp391.learning.domain.dto.request.admin.book.BookRequest;
import swp391.learning.domain.dto.request.admin.book.DeleteBookRequest;
import swp391.learning.domain.dto.request.admin.book.UpdateBookRequest;
import swp391.learning.domain.dto.request.user.book.SearchBookByNameAndCategoryRequest;
import swp391.learning.domain.dto.response.admin.book.AddBookResponse;
import swp391.learning.domain.dto.response.admin.book.DeleteBookResponse;
import swp391.learning.domain.dto.response.user.book.SearchBookByNameAndCategoryResponse;
import swp391.learning.domain.dto.response.admin.book.UpdateBookResponse;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.InvalidRequestException;
import swp391.learning.exception.ResourceNotFoundException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
@Slf4j
public class BookController {
    private BookService bookService;
//    private final int TOP_BOOK = 10;

    @Operation(summary = "Add book")
    @PostMapping("/add-book")
    public ResponseSuccess<?> addBook(@Valid @RequestBody BookRequest addBookRequest){
        log.info("Add book");
        try {
            bookService.addBook(addBookRequest);
            return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Add book " + addBookRequest.getTitle() + " success");
        } catch (ResourceNotFoundException e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (DuplicateResourceException e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage());
        } catch (InvalidRequestException e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add book fail");
        }
    }

//    @PostMapping("/update-book")
//    public ResponseEntity<ResponseCommon<UpdateBookResponse>> updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest,
//                                                                         @RequestParam("image") MultipartFile file){
//        ResponseCommon<UpdateBookResponse> response = bookService.updateBook(updateBookRequest,file);
//        // if code of response equal code success -> return ok
//        if(response.getCode()==ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok(response);
//        } // if code equal Book not exist -> tell error
//        else if (response.getCode()==ResponseCode.BOOK_NOT_EXIST.getCode()) {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(response.getCode(),"Book not exist",null));
//        } // else -> return fail  update
//        else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Update book fail",null));
//        }
//    }
//
//    @PostMapping("/delete-book")
//    public ResponseEntity<ResponseCommon<DeleteBookResponse>> deleteBook(@Valid @RequestBody DeleteBookRequest deleteBookRequest){
//        ResponseCommon<DeleteBookResponse> response = bookService.deleteBook(deleteBookRequest);
//        // if code of response equal code success -> return ok
//        if(response.getCode()==ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok(response);
//        } // if code equal Book not exist -> tell error
//        else if (response.getCode()==ResponseCode.BOOK_NOT_EXIST.getCode()) {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(response.getCode(),"Book not exist",null));
//        } // else -> return fail  update
//        else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Delete Book fail",null));
//        }
//    }
//    @GetMapping("/search-book")
//    public ResponseEntity<ResponseCommon<SearchBookByNameAndCategoryResponse>> searchBook(SearchBookByNameAndCategoryRequest searchBookByNameAndCategoryRequest){
//        ResponseCommon<SearchBookByNameAndCategoryResponse> response = bookService.searchBook(searchBookByNameAndCategoryRequest);
//        // if response code quals empty list code -> tell user
//        if(response.getCode() == ResponseCode.BOOK_LIST_IS_EMPTY.getCode()){
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.BOOK_LIST_IS_EMPTY.getCode(),"Book list is empty",null));
//        } else if(response.getCode() == ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok().body(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(),"Search book success",response.getData()));
//        } else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Search book fail",null));
//        }
//    }

}
