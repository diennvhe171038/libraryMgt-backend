package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.AuthorService;
import swp391.learning.domain.dto.request.admin.author.AddAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.DeleteAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.UpdateAuthorRequest;
import swp391.learning.domain.dto.response.admin.author.FindAllAuthorResponse;
import swp391.learning.domain.dto.response.admin.category.FindAllCategoryResponse;
import swp391.learning.domain.entity.Author;
import swp391.learning.domain.entity.Category;
import swp391.learning.domain.entity.User;
import swp391.learning.exception.DuplicateResourceException;
import swp391.learning.exception.ResourceNotFoundException;
import swp391.learning.repository.AuthorRepository;
import swp391.learning.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    private final UserRepository userRepository;

    @Override
    public void addAuthor(AddAuthorRequest addAuthorRequest) {
        Author author = authorRepository.findAuthorByName(addAuthorRequest.getNameAuthor());
        User user = userRepository.findById(addAuthorRequest.getCreatedBy());

        if(author != null){
            log.debug("Add Author failed: Author already exists");
            throw new DuplicateResourceException("Tác giả " + author.getName() + " đã tồn tại");
        }

        if(user == null){
            log.debug("Add Author failed: User is null");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        Author newAuthor = new Author();
        newAuthor.setName(addAuthorRequest.getNameAuthor());
        newAuthor.setCreatedBy(user);
        newAuthor.setDesc(addAuthorRequest.getDescription());

        authorRepository.save(newAuthor);
    }

    @Override
    public void updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
        Author author = authorRepository.findById(updateAuthorRequest.getAuthorId());
        User user = userRepository.findById(updateAuthorRequest.getUpdatedBy());

        if(author == null){
            log.debug("Update Author failed: Author does not exist");
            throw new ResourceNotFoundException("Tác giả không tồn tại");
        }

        if(user == null){
            log.debug("Update Author failed: User is null");
            throw new ResourceNotFoundException("Người dùng không tồn tại");
        }

        author.setName(updateAuthorRequest.getNameAuthor());
        author.setUpdatedBy(user);
        author.setDesc(updateAuthorRequest.getDescription());

        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {
        Author author = authorRepository.findById(deleteAuthorRequest.getAuthorID());

        if(author == null){
            log.debug("Delete Author failed: Author does not exist");
            throw new ResourceNotFoundException("Tác giả không tồn tại");
        }

        authorRepository.delete(author);
    }

    @Override
    public List<FindAllAuthorResponse> findAllAuthor() {
        List<Author> authors = authorRepository.findAll();

        if(authors.isEmpty()){
            log.debug("Find All Author failed: Author is empty");
            throw new ResourceNotFoundException("Chưa có tác giả nào trong danh sách");
        }

        return authors.stream()
                .map(this::mapToAuthorResponse)
                .collect(Collectors.toList());
    }

    private FindAllAuthorResponse mapToAuthorResponse(Author author) {
        return FindAllAuthorResponse.builder()
                .authorId(author.getId())
                .authorName(author.getName())
                .description(author.getDesc())
                .updatedBy(author.getUpdatedBy().getId())
                .fullName(author.getUpdatedBy().getFullName())
                .build();
    }


}
