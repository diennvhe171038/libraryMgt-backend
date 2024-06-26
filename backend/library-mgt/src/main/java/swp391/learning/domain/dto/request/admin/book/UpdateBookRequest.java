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
@Getter
@Setter
public class UpdateBookRequest {
        @NotBlank
        private String email;
        @NotNull
        private int bookID;
        @NotBlank
        private String nameBook;
        @NotBlank
        private String description;
        @NotNull
        private double price;
        @NotNull
        private int stock;
        @NotBlank
        private String imagePath;
        @NotBlank
        private String ISBN;
        @NotNull
        private boolean deleted;
        @NotBlank
        private Author author;
        @NotBlank
        private Category category;
        @NotBlank
        private String totalPage;
        @NotBlank
        private String language;
        @NotBlank
        private String publisher;
        @NotBlank
        private String publicationYear;
}
