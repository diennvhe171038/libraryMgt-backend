package swp391.learning.domain.dto.response.admin.BookCopy;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class BookCopyResponse {
    private int id;
    private int userId;
    private String barcode;
    private String status;
    private String updatedBy;
    private String updatedAt;
}
