package swp391.learning.application.service;

import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.DeleteCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.UpdateCategoryResponse;

public interface CategoryBookService {
    ResponseCommon<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest);

    ResponseCommon<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest addCategoryRequest);

    ResponseCommon<DeleteCategoryResponse> deleteCategory(DeleteCategoryRequest deleteCategoryRequest);

    ResponseCommon<FindAllCategoryResponse> findAllCategory();

}
