package swp391.learning.domain.dto.response.admin.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.entity.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindAllCategoryResponse {
    @NotBlank
    private String message;
    @NotEmpty
    private List<Category> categoryList;

}
