package swp391.learning.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.learning.application.service.CategoryService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.common.ResponseError;
import swp391.learning.domain.dto.common.ResponseSuccess;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.DeleteCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.UpdateCategoryResponse;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
@Log4j2
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseSuccess<?> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest){
        try{
            categoryService.addCategory(addCategoryRequest);
            return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Thêm danh mục " + addCategoryRequest.getName() + " thành công");

        } catch (DuplicateResourceException e){
            return new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage());
        } catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Thêm danh mục thất bại");
        }
    }

    @PutMapping("/update-category/{id}")
    public ResponseSuccess<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable int id){
        try{
            updateCategoryRequest.setCategoryId(id);
            categoryService.updateCategory(updateCategoryRequest);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Sửa danh mục " + updateCategoryRequest.getName() + " thành công");
        } catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Sửa danh mục thất bại");
        }
    }

    @DeleteMapping("/delete-category")
    public ResponseSuccess<?> deleteCategory(@Valid @RequestBody DeleteCategoryRequest deleteCategoryRequest){
        try{
            categoryService.deleteCategory(deleteCategoryRequest);
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Xoá danh mục thành công");
        } catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Xoá danh mục thất bại");
        }
    }

    @GetMapping("/find-all-category")
    public ResponseSuccess<?> getAllCategories() {
        try {
            List<FindAllCategoryResponse> response = categoryService.findAllCategory();
            return new ResponseSuccess<>(HttpStatus.OK.value(), "Lấy danh sách danh mục thành công", response);
        } catch (ResourceNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy danh sách danh mục thất bại");
        }
    }
}
