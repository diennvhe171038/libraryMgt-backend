package swp391.learning.domain.dto.response.admin.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import swp391.learning.domain.entity.Author;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class FindAllAuthorResponse {
    private int authorId;
    private String authorName;
    private String description;
    private int modifiedById;
}
