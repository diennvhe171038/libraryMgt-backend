package swp391.learning.domain.dto.response.admin.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddAuthorResponse {
    private int authorID;
    private String nameAuthor;
    private String describe;
    private double price;
    private String link_thumnail;
    private LocalDateTime updatedAt;

}
