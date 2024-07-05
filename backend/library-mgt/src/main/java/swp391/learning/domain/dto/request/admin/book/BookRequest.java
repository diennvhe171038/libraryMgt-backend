package swp391.learning.domain.dto.request.admin.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookRequest {
    @NotNull
    private int userId;

    @NotBlank
    private String isbn;

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int totalPage;

    @NotBlank
    private String language;

    @NotBlank
    private String publisher;

    @NotNull
    private int publicationYear;

    @NotNull
    private String description;

    @NotNull
    private MultipartFile bookImage;

    @NotEmpty
    private Set<MultipartFile> sampleBookImages;

    @NotEmpty
    private Set<Integer> authorIds;

    @NotEmpty
    private Set<Integer> categoryIds;
}
