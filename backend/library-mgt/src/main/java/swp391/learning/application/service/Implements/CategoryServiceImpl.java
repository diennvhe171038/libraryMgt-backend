package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.CategoryService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.category.AddCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.DeleteCategoryRequest;
import swp391.learning.domain.dto.request.admin.category.UpdateCategoryRequest;
import swp391.learning.domain.dto.response.admin.category.AddCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.DeleteCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.dto.response.admin.category.UpdateCategoryResponse;
import swp391.learning.domain.dto.response.user.authentication.UserResponse;
import swp391.learning.domain.entity.Category;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.ResponseCode;
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

            Category parentCategory = categoryRepository.findCategoryById(addCategoryRequest.getParentId());
            Category category = categoryRepository.findCategoryByName(addCategoryRequest.getName());
            User user = userRepository.findById(addCategoryRequest.getCreatedById());

            if (category != null) {
                log.debug("Add Category failed: Category already exists");
                throw new DuplicateResourceException("Danh mục " + category.getName() + " đã tồn tại");
            }

            if(user == null){
                log.debug("Add Category failed: User is null");
                throw new ResourceNotFoundException("Người dùng không tồn tại");
            }

            Category newCategory = new Category();
            newCategory.setName(addCategoryRequest.getName());
            newCategory.setCreatedBy(user);
            if(parentCategory != null) {
                newCategory.setParentCategory(parentCategory);
            }

            categoryRepository.save(newCategory);
    }

    @Override
    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findCategoryById(updateCategoryRequest.getCategoryId());
        User user = userRepository.findById(updateCategoryRequest.getUpdatedById());

        if(category == null){
            log.debug("Update Category failed: Category does not exist");
            throw new ResourceNotFoundException("Danh mục không tồn tại");
        }

        if(user == null){
            log.debug("Update Category failed: User is null");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        category.setName(updateCategoryRequest.getName());
        category.setUpdatedBy(user);
        if(category.getParentCategory() != null) {
            category.setParentCategory(category.getParentCategory());
        }

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
        Category category = categoryRepository.findCategoryById(deleteCategoryRequest.getCategoryId());

        if(category == null){
            log.debug("Delete Category failed: Category does not exist");
            throw new ResourceNotFoundException("Danh mục không tồn tại");
        }

        categoryRepository.delete(category);
    }

    @Override
    public List<FindAllCategoryResponse> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()){
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
                .parentId(category.getParentCategory() != null ? category.getParentCategory().getId() : 0)
                .createdById(category.getCreatedBy() != null ? category.getCreatedBy().getId() : 0)
                .build();
    }


//    @Override
//    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {
//        try {
//            Category category = categoryRepository.findCategoryById(updateCategoryRequest.getCategoryID()).orElse(null);
//            User user = userRepository.findByEmail(updateCategoryRequest.getEmail());
//            // if category is null -> tell user
//            if (Objects.isNull(category)) {
//                log.debug("Update Category failed: Category does not exist");
//                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
//            } else {
//                //tạo mảng để lưu dữ liệu mới
//                Category categoryUpdate = category;
//                categoryUpdate.setName(updateCategoryRequest.getCategoryUpdate());
//                categoryUpdate.setUpdatedAt(LocalDateTime.now());
//                categoryUpdate.setDeleted(updateCategoryRequest.isDeleted());
//                categoryUpdate.setUserUpdated(user);
//                categoryRepository.save(categoryUpdate);
//                //update du lieu
//                UpdateCategoryResponse updateCategoryResponse = new UpdateCategoryResponse();
//                updateCategoryResponse.setCategoryID(categoryUpdate.getId());
//                updateCategoryResponse.setCategoryName(categoryUpdate.getName());
//                log.debug("Update Category successful");
//                return new ResponseCommon<>(ResponseCode.SUCCESS, updateCategoryResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug("Update Category failed: " + e.getMessage());
//            return new ResponseCommon<>(ResponseCode.FAIL, null);
//        }
//    }
//
//    @Override
//    public void deleteCategory(DeleteCategoryRequest deleteCategoryRequest) {
//        try {
//            Category category = categoryRepository.findCategoryById(deleteCategoryRequest.getCategoryID()).orElse(null);
//            User user = userRepository.findByEmail(deleteCategoryRequest.getEmail());
//            // if category is null -> tell the user
//            if (Objects.isNull(category)) {
//                log.debug("Delete Category failed: Category does not exist");
//                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
//            } else {
//                Category categoryUpdate = category;
//                categoryUpdate.setDeleted(true);
//                categoryUpdate.setUpdatedAt(LocalDateTime.now());
//                categoryUpdate.setUserUpdated(user);
//                categoryRepository.save(categoryUpdate);
//
//                DeleteCategoryResponse deleteCategoryResponse = new DeleteCategoryResponse();
//                deleteCategoryResponse.setCategoryID(categoryUpdate.getId());
//                deleteCategoryResponse.setCategoryName(categoryUpdate.getName());
//                deleteCategoryResponse.setDeleted(categoryUpdate.isDeleted());
//                log.debug("Delete Category successful");
//                return new ResponseCommon<>(ResponseCode.SUCCESS, deleteCategoryResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug("Delete Category failed: " + e.getMessage());
//            return new ResponseCommon<>(ResponseCode.FAIL, null);
//        }
//    }

//    @Override
//    public ResponseCommon<FindAllCategoryResponse> findAllCategory() {
//        try {
//            List<Category> listCategory = categoryRepository.findAllByIsDeleted(false);
//            // if the list is empty -> tell the user
//            if (listCategory.isEmpty()) {
//                log.debug("Get all Category failed: Category list is empty");
//                return new ResponseCommon<>(ResponseCode.CATEGORY_LIST_IS_EMPTY, null);
//            } else {
//                FindAllCategoryResponse response = new FindAllCategoryResponse("Get all success", listCategory);
//                log.debug("Get all Category successful");
//                return new ResponseCommon<>(ResponseCode.SUCCESS, response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug("Get all Category failed: " + e.getMessage());
//            return new ResponseCommon<>(ResponseCode.FAIL, null);
//        }
//    }
}
