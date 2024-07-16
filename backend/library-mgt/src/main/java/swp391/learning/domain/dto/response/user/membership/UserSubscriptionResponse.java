package swp391.learning.domain.dto.response.user.membership;

import lombok.*;
import swp391.learning.domain.entity.MemberSubscription;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class UserSubscriptionResponse {
    private int id;
    private int userId;
    private List<MemberSubscription> memberSubscriptions;
    private String startDate;
    private String endDate;
    private boolean isActive;
}
