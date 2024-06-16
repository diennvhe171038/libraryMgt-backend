package swp391.learning.domain.dto.response.admin.book;

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
public class UpdateBookResponse {
    @NotNull
    private int bookID;
    @NotBlank
    private String bookName;
    @NotBlank
    private String description;
    @NotNull
    private double price;
    @NotEmpty
    private Category category;
    @NotBlank
    private String linkThumail;
    @NotNull
    private LocalDateTime updateAt;
    @NotNull
    private LocalDateTime createAt;
    @NotBlank
    private String createdBy;
    @NotBlank
    private String updatedBy;
}
