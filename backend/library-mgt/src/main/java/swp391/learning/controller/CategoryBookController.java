package swp391.learning.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.learning.application.service.CategoryBookService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.enums.ResponseCode;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Log4j2
public class CategoryBookController {
    private final CategoryBookService bookCategoryService;

    @PostMapping("/add-category")
    public ResponseEntity<ResponseCommon<AddCategoryResponse>> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest){
        ResponseCommon<AddCategoryResponse> response = bookCategoryService.addCategory(addCategoryRequest);
        // if code response equals code Success -> return ok
        if(response.getCode() == ResponseCode.SUCCESS.getCode()){
            return ResponseEntity.ok(response);
        } // if code response equals code category exist -> return error
        else if(response.getCode() == ResponseCode.CATEGORY_EXIST.getCode()){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseCommon<>(ResponseCode.CATEGORY_EXIST.getCode(),"Category already exist",null));
        } // else return fail
        else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"System error",null));
        }
    }
}