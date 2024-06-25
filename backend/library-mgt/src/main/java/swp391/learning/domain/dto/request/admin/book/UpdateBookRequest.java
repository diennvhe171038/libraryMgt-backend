package swp391.learning.domain.dto.request.admin.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        private String name;
        @NotBlank
        private String description;
        @NotNull
        private double price;
        @NotNull
        private int stock;
        @NotBlank
        private Blob image;
        @NotNull
        private int categoryID;
        @NotNull
        private boolean deleted;
}
