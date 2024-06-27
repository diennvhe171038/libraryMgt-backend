package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.CategoryService;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.entity.Category;
import swp391.learning.domain.entity.User;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;
import swp391.learning.repository.CategoryRepository;
import swp391.learning.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public void addCategory(AddCategoryRequest addCategoryRequest) {

        Category category = categoryRepository.findCategoryByName(addCategoryRequest.getName());
        User user = userRepository.findById(addCategoryRequest.getCreatedBy());

        if (user == null) {
            log.debug("Add Category failed: User is null");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        if (category != null) {
            log.debug("Add Category failed: Category already exists");
            throw new DuplicateResourceException("Danh mục " + category.getName() + " đã tồn tại");
        } else {
            Category newCategory = new Category();
            newCategory.setName(addCategoryRequest.getName());
            newCategory.setCreatedBy(user);

            categoryRepository.save(newCategory);
        }



    }

    @Override
    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findCategoryById(updateCategoryRequest.getCategoryId());
        User user = userRepository.findById(updateCategoryRequest.getUpdatedBy());

        if (user == null) {
            log.debug("Update Category failed: User is null");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        if (category != null) {
            log.debug("Update Category failed: Category does not exist");
            throw new DuplicateResourceException("Danh mục " + category.getName() + " đã tồn tại");
        } else {
            category.setName(updateCategoryRequest.getName());
            category.setUpdatedBy(user);

            categoryRepository.save(category);
        }




    }

    @Override
    public void deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        Category category = categoryRepository.findCategoryById(deleteCategoryRequest.getCategoryId());

        if (category == null) {
            log.debug("Delete Category failed: Category does not exist");
            throw new ResourceNotFoundException("Danh mục không tồn tại");
        }

        categoryRepository.delete(category);
    }

    @Override
    public List<FindAllCategoryResponse> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            log.debug("Find All Category failed: Category is empty");
            throw new ResourceNotFoundException("Danh mục rỗng");
        }


        return categories.stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    private FindAllCategoryResponse mapToCategoryResponse(Category category) {
        return FindAllCategoryResponse.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .updatedBy(category.getUpdatedBy().getId())
                .fullName(category.getUpdatedBy().getFullName())
                .build();
    }

}
