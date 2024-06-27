package swp391.learning.domain.dto.response.admin.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import swp391.learning.domain.entity.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class FindAllCategoryResponse {
    private int categoryId;
    private String categoryName;
    private int updatedBy;
    private String fullName;
}
