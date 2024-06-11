package swp391.learning.domain.dto.response.admin.category;

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
public class AddCategoryResponse {
    @NotNull
    private int categoryID;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String createdBy;
    @NotBlank
    private String updatedBy;

}