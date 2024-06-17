package swp391.learning.domain.dto.request.admin.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddBookRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private double price;
    @NotBlank
    private String link_thumnail;
    @NotBlank
    private String category;

}
