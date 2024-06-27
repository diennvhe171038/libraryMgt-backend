package swp391.learning.application.service;

import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.DeleteCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.UpdateCategoryResponse;
import swp391.learning.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(AddCategoryRequest addCategoryRequest);

    void updateCategory(UpdateCategoryRequest addCategoryRequest);

    void deleteCategory(DeleteCategoryRequest deleteCategoryRequest);

    List<FindAllCategoryResponse> findAllCategory();

}
