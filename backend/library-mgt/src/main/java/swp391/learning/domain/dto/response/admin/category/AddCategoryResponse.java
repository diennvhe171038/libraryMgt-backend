package swp391.learning.domain.dto.response.admin.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCategoryResponse {
    private int categoryID;
    private String categoryName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedAt;
}