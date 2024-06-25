package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.AuthorService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.author.AddAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.DeleteAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.UpdateAuthorRequest;
import swp391.learning.domain.dto.response.admin.author.AddAuthorResponse;
import swp391.learning.domain.dto.response.admin.author.DeleteAuthorResponse;
import swp391.learning.domain.dto.response.admin.author.UpdateAuthorResponse;
import swp391.learning.domain.entity.Author;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.repository.AuthorRepository;
import swp391.learning.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Override
    public ResponseCommon<AddAuthorResponse> addAuthor(AddAuthorRequest addAuthorRequest) {
        try {
            Author author = authorRepository.findAuthorByName(addAuthorRequest.getNameAuthor()).orElse(null);
            User user = userRepository.findByEmail(addAuthorRequest.getEmail());
            // if category not null -> tell user
            if (!Objects.isNull(author)) {
                log.debug("Add author failed: author already exists");
                return new ResponseCommon<>(ResponseCode.AUTHOR_EXIST, null);
            }
            // if category is null -> new category
            if (Objects.isNull(author)) {
                author = new Author();
            }
            author.setName(addAuthorRequest.getNameAuthor());
            author.setDeleted(false);
            author.setLink_Thumnail(addAuthorRequest.getLink_Thumnail());
            author.setDesc(addAuthorRequest.getDescribe());
            LocalDateTime localDateTime = LocalDateTime.now();
            author.setUpdatedAt(localDateTime.now());
            author.setUserCreated(user);
            // Save category to the database
            Author savedAuthor = authorRepository.save(author);

            // If category is not saved successfully, return a FAIL response
            if (savedAuthor == null) {
                log.debug("Add author failed: Unable to save the author");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }
            AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
            addAuthorResponse.setMessage("Add author successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, addAuthorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Add Category failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<UpdateAuthorResponse> updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
        try {
            Author author = authorRepository.findAuthorById(updateAuthorRequest.getAuthorID()).orElse(null);
            User user = userRepository.findByEmail(updateAuthorRequest.getEmail());
            // if category is null -> tell user
            if (Objects.isNull(author)) {
                log.debug("Update Category failed: Category does not exist");
                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
            } else {
                //tạo mảng để lưu dữ liệu mới
                Author authorUpdate = author;
                authorUpdate.setName(updateAuthorRequest.getNameAuthor());
                authorUpdate.setUpdatedAt(LocalDateTime.now());
                authorUpdate.setDeleted(updateAuthorRequest.isDeleted());
                authorUpdate.setDesc(updateAuthorRequest.getDescription());
                authorUpdate.setLink_Thumnail(updateAuthorRequest.getLink_thumnail());
                authorUpdate.setUserUpdated(user);
                authorRepository.save(authorUpdate);
                //update du lieu
                UpdateAuthorResponse updateAuthorResponse = new UpdateAuthorResponse();
                updateAuthorResponse.setMessage("Update author successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, updateAuthorResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Update author failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<DeleteAuthorResponse> deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {
        try {
            Author author = authorRepository.findAuthorById(deleteAuthorRequest.getAuthorID()).orElse(null);
            User user = userRepository.findByEmail(deleteAuthorRequest.getEmail());
            // if category is null -> tell the user
            if (Objects.isNull(author)) {
                log.debug("Delete Category failed: Category does not exist");
                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
            } else {
                Author authorUpdate = author;
                authorUpdate.setDeleted(true);
                authorUpdate.setUpdatedAt(LocalDateTime.now());
                authorUpdate.setUserUpdated(user);
                authorRepository.save(authorUpdate);

                DeleteAuthorResponse deleteAuthorResponse = new DeleteAuthorResponse();
                deleteAuthorResponse.setMessage("Delete Category successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, deleteAuthorResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Delete Category failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
}
