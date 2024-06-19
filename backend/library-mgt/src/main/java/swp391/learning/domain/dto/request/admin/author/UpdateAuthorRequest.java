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
    @NotBlank
    private String email; // userName nguoi sua
    @NotNull
    private int authorID;
    @NotBlank
    private String nameAuthor; // ten tac gia
    @NotBlank
    private String description;
    @NotBlank
    private String link_thumnail;
    @NotNull
    private boolean deleted;
}
