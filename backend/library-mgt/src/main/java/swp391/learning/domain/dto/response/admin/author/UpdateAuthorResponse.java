package swp391.learning.domain.dto.response.admin.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.entity.Category;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateAuthorResponse {
    @NotNull
    private int authorID;
    @NotBlank
    private String nameAuthor;
    @NotBlank
    private String description;
    @NotBlank
    private String linkThumail;
    @NotNull
    private LocalDateTime updateAt; // thoi gian cap nhat
    @NotNull
    private LocalDateTime createAt; // thoi gian tao
    @NotBlank
    private String createdBy; // nguoi tao
    @NotBlank
    private String updatedBy; // nguoi sua
}
