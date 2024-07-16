package swp391.learning.domain.dto.response.admin.membership;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class SubscriptionResponse {
    private int id;
    private int userId;
    private String name;
    private double feeMember;
    private int loanDuration;
    private boolean isDeleted;
    private String updatedBy;
    private String updatedAt;
}
