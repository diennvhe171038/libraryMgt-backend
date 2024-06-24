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

//    public ResponseEntity<ResponseCommon<UpdateCategoryResponse>> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
//        ResponseCommon<UpdateCategoryResponse> response = categoryService.updateCategory(updateCategoryRequest);
//        // if code response equals code succes -> return ok
//        if(response.getCode() == ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok(response);
//        } // if code response equals code category not exist -> return error
//        else if(response.getCode() == ResponseCode.CATEGORY_NOT_EXIST.getCode()){
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST.getCode(),"Category not exist",null));
//        } // else - return fail
//        else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Update Fail",null));
//
//        }
//    }
//    @PostMapping("/delete-category")
//    public ResponseEntity<ResponseCommon<DeleteCategoryResponse>> deleteCategory(@Valid @RequestBody DeleteCategoryRequest deleteCategoryRequest){
//        ResponseCommon<DeleteCategoryResponse> response = categoryService.deleteCategory(deleteCategoryRequest);
//        // if code response equals code succes -> return ok
//        if(response.getCode() == ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok(response);
//        } // if code response equals code category not exist -> return error
//        else if(response.getCode() == ResponseCode.CATEGORY_NOT_EXIST.getCode()){
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST.getCode(),"Category not exist",null));
//        } // else - return fail
//        else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Delete Fail",null));
//        }
//    }
//    @GetMapping("/find-all-category")
//    public ResponseEntity<ResponseCommon<FindAllCategoryResponse>> findAllCategory(){
//        ResponseCommon<FindAllCategoryResponse> response = categoryBookService.findAllCategory();
//        // if code response equals code success -> return ok
//        if(response.getCode()==ResponseCode.SUCCESS.getCode()){
//            return ResponseEntity.ok(response);
//        } else if(response.getCode()==ResponseCode.CATEGORY_LIST_IS_EMPTY.getCode()){
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.CATEGORY_LIST_IS_EMPTY.getCode(),"List Category is Empty",null));
//        } else {
//            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Find All category fail",null));
//        }
//    }
}
