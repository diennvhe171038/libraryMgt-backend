package swp391.learning.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import swp391.learning.application.service.AuthorService;
import swp391.learning.domain.dto.common.ResponseError;
import swp391.learning.domain.dto.common.ResponseSuccess;
import swp391.learning.domain.dto.request.admin.author.AddAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.DeleteAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.UpdateAuthorRequest;
import swp391.learning.domain.dto.response.admin.author.FindAllAuthorResponse;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
@Log4j2
public class AuthorController {
    private AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseSuccess<?> addAuthor(@Valid @RequestBody AddAuthorRequest addAuthorRequest) {
        try {
            authorService.addAuthor(addAuthorRequest);
            return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Thêm tác giả " + addAuthorRequest.getNameAuthor() + " thành công");

        } catch (DuplicateResourceException e) {
            return new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage());
        } catch (ResourceNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Thêm tác giả thất bại");
        }
    }

    @PutMapping("/update-author/{id}")
    public ResponseSuccess<?> updateAuthor(@Valid @RequestBody UpdateAuthorRequest updateAuthorRequest, @PathVariable int id){
        try{
            updateAuthorRequest.setAuthorId(id);
            authorService.updateAuthor(updateAuthorRequest);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Sửa thông tin tác giả " + updateAuthorRequest.getNameAuthor() + " thành công");
        } catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Sửa thông tin tác giả thất bại");
        }
    }

    @DeleteMapping("/delete-author")
    public ResponseSuccess<?> deleteAuthor(@Valid @RequestBody DeleteAuthorRequest deleteAuthorRequest){
        try{
            authorService.deleteAuthor(deleteAuthorRequest);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Xoá tác giả thành công");
        } catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Xoá tác giả thất bại");
        }
    }

    @GetMapping("/find-all-author")
    public ResponseSuccess<?> getAllAuthor() {
        try {
            List<FindAllAuthorResponse> response = authorService.findAllAuthor();
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Lấy danh sách tác giả thành công", response);
        } catch (ResourceNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy danh sách tác giả thất bại");
        }
    }
}


