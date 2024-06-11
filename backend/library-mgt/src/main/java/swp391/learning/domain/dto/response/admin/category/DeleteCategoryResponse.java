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
public class DeleteCategoryResponse {
    @NotNull
    private int categoryID;
    @NotBlank
    private String categoryName;
    @NotNull
    private boolean isDeleted;
    @NotBlank
    private String createdBy;
    @NotBlank
    private String updatedBy;
}

