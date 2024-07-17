package swp391.learning.domain.dto.response.user.membership;

import lombok.*;
import swp391.learning.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MembershipResponse {
    private int id;
    private String nameSubscription; // tên gói
    private double feeMember; // phí gói thành viên
    private boolean isDeleted = false;
    private int maxBook;
    private int expireDate;
    private LocalDateTime createdAt; // ngay goi duoc tạo ra
    private User userCreated;
    private User userUpdated;
    private List<BenefitDTO> benefits;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class BenefitDTO {
        private int id;
        private String name;
    }
}
