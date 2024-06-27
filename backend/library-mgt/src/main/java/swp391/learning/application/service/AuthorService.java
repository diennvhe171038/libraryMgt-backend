package swp391.learning.application.service;

import org.springframework.stereotype.Service;
import swp391.learning.domain.dto.request.admin.author.AddAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.DeleteAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.UpdateAuthorRequest;
import swp391.learning.domain.dto.response.admin.author.FindAllAuthorResponse;

import java.util.List;


public interface AuthorService {
    void addAuthor(AddAuthorRequest addAuthorRequest);

    void updateAuthor(UpdateAuthorRequest updateAuthorRequest);

    void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest);

    List<FindAllAuthorResponse> findAllAuthor();

}
