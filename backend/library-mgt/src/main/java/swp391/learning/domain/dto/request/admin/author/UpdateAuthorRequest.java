package swp391.learning.domain.dto.request.admin.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAuthorRequest {
    @NotNull
    private int authorId;
    @NotBlank
    private String nameAuthor;
    @NotBlank
    private String description;
    @NotNull
    private int updatedBy;
}
