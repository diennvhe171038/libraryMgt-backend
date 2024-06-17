package swp391.learning.domain.dto.request.admin.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddAuthorRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String nameAuthor;
    @NotBlank
    private String link_Thumnail;
    @NotBlank
    private String describe;
}
