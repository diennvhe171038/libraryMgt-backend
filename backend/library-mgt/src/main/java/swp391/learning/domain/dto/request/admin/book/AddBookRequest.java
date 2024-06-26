package swp391.learning.domain.dto.request.admin.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.entity.Author;
import swp391.learning.domain.entity.Category;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddBookRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String ISBN;
    @NotBlank
    private String description;
    @NotNull
    private double price;
    @NotBlank
    private String imagePath;
    @NotBlank
    private Author author;
    @NotBlank
    private Category category;
    @NotNull
    private int stock;
    @NotBlank
    private String totalPage;
    @NotBlank
    private String language;
    @NotBlank
    private String publisher;
    @NotBlank
    private String publicationYear;
}
