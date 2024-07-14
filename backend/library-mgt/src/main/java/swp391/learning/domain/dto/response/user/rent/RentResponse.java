package swp391.learning.domain.dto.response.user.rent;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.dto.response.admin.BookCopy.BookCopyResponse;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentResponse {
    private int loanId;
    private BookCopyResponse bookCopyResponse;
    private int memberId;
    private double memFee;
    private double price;
    private int userId;
    private String status;
    private String note; // ghi chú
    private LocalDateTime borrowAt; // thời gian mượn
    private LocalDateTime returnAt; // thời gian trả
    private LocalDateTime dueDate; // thời hạn hết hạn trả sách

}
