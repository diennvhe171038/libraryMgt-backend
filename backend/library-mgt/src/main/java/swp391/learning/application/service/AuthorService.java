package swp391.learning.application.service;

import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.author.AddAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.DeleteAuthorRequest;
import swp391.learning.domain.dto.request.admin.author.UpdateAuthorRequest;
import swp391.learning.domain.dto.response.admin.author.AddAuthorResponse;
import swp391.learning.domain.dto.response.admin.author.DeleteAuthorResponse;
import swp391.learning.domain.dto.response.admin.author.UpdateAuthorResponse;

public interface AuthorService {
    ResponseCommon<AddAuthorResponse> addAuthor(AddAuthorRequest addAuthorRequest);

    ResponseCommon<UpdateAuthorResponse> updateAuthor(UpdateAuthorRequest updateAuthorRequest);

    ResponseCommon<DeleteAuthorResponse> deleteAuthor(DeleteAuthorRequest deleteAuthorRequest);

}
