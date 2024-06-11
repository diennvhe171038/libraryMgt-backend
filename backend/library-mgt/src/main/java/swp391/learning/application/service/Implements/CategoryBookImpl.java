package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.CategoryBookService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.DeleteCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.UpdateCategoryResponse;

@Service
@RequiredArgsConstructor
public class CategoryBookImpl implements CategoryBookService {
    @Override
    public ResponseCommon<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) {
        return null;
    }

    @Override
    public ResponseCommon<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest addCategoryRequest) {
        return null;
    }

    @Override
    public ResponseCommon<DeleteCategoryResponse> deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        return null;
    }

    @Override
    public ResponseCommon<FindAllCategoryResponse> findAllCategory() {
        return null;
    }
}
