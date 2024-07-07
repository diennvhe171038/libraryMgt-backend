package swp391.learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import swp391.learning.domain.entity.Book;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.InvalidRequestException;
import swp391.learning.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
@Slf4j
public class BookController {
    private BookService bookService;
//    private final int TOP_BOOK = 10;

    @Operation(summary = "Add book")
    @PostMapping("/add-book")
    public ResponseSuccess<?> addBook(@RequestBody BookRequest addBookRequest) {
        log.info("Add book");
        try {
            Book book = bookService.addBook(addBookRequest);
            return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Thêm sách thành công", book.getId());
        } catch (ResourceNotFoundException e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (DuplicateResourceException e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Add book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Thêm sách thất bại");
        }
    }

    @Operation(summary = "Update book")
    @PutMapping("/{bookId}/update-book")
    public ResponseSuccess<?> updateBook(@PathVariable int bookId, @RequestBody BookRequest updateBookRequest) {
        log.info("Update book");
        try {
            Book book = bookService.updateBook(bookId, updateBookRequest);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Cập nhật sách thành công", book.getId());
        } catch (ResourceNotFoundException e) {
            log.error("Update book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Update book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Cập nhật sách thất bại");
        }
    }

    @Operation(summary = "Delete book")
    @DeleteMapping("/{bookId}/delete-book")
    public ResponseSuccess<?> deleteBook(@PathVariable int bookId) {
        log.info("Delete book");
        try {
            bookService.deleteBook(bookId);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Xóa sách thành công");
        } catch (ResourceNotFoundException e) {
            log.error("Delete book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Delete book failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Xóa sách thất bại");
        }
    }

    @Operation(summary = "Upload book image")
    @PostMapping("/{bookId}/upload-book-image")
    public ResponseSuccess<?> uploadBookImage(@PathVariable int bookId, @RequestParam("file") MultipartFile file) {
        log.info("Upload book image");
        try {
            bookService.uploadBookImage(bookId, file);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Tải ảnh sách thành công");
        } catch (ResourceNotFoundException e) {
            log.error("Upload book image failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Upload book image failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Tải ảnh sách thất bại");
        }
    }

    @Operation(summary = "Upload sample book images")
    @PostMapping("/{bookId}/upload-sample-book-images")
    public ResponseSuccess<?> uploadSampleBookImages(@PathVariable int bookId, @RequestParam("files") Set<MultipartFile> files) {
        log.info("Upload sample book images");
        try {
            bookService.uploadSampleBookImages(bookId, files);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Tải ảnh mẫu sách thành công");
        } catch (ResourceNotFoundException e) {
            log.error("Upload sample book images failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Upload sample book images failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Tải ảnh mẫu sách thất bại");
        }
    }

    @Operation(summary = "get books")
    @GetMapping("/get-books")
    public ResponseSuccess<?> getBooks(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                       @Min(4) @RequestParam(defaultValue = "10", required = false) int pageSize,
                                       @RequestParam(required = false) String search,
                                       @RequestParam(required = false) Integer categoryId) {

        log.info("Get books");
        try {
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Lấy danh sách sách thành công", bookService.getBooks(pageNo, pageSize, search, categoryId));
        } catch (Exception e) {
            log.error("Get books failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy danh sách sách thất bại");
        }
    }

    @Operation(summary = "Get book by id")
    @GetMapping("/{bookId}/get-book-by-id")
    public ResponseSuccess<?> getBookById(@PathVariable int bookId) {
        log.info("Get book by id");
        try {
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Lấy sách thành công", bookService.getBookById(bookId));
        } catch (ResourceNotFoundException e) {
            log.error("Get book by id failed: " + e.getMessage());
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            log.error("Get book by id failed: " + e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy sách thất bại");
        }
    }

    @Operation(summary = "Get book image")
    @GetMapping("/{bookId}/get-book-image")
    public ResponseEntity<Resource> getBookImage(@PathVariable int bookId) {
        log.info("Get book image");
        try {
            Resource resource = bookService.getBookImage(bookId);
            log.info("Get book image success: " + resource.getFilename() + " - " + resource.contentLength() + " bytes ");
            log.info("Header Content-Disposition: attachment; filename=\"" + resource.getFilename() + "\"");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (ResourceNotFoundException e) {
            log.error("Get book image failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Get book image failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Get sample book images")
    @GetMapping("/{bookId}/get-sample-book-images")
    public ResponseEntity<Resource> getSampleBookImages(@PathVariable int bookId) {
        log.info("Get sample book images");
        try {
            Resource resource = bookService.getSampleBookImages(bookId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (ResourceNotFoundException e) {
            log.error("Get sample book images failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Get sample book images failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
