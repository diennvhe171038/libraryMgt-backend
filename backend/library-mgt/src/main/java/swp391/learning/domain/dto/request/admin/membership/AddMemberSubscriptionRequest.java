package swp391.learning.domain.dto.request.admin.membership;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddMemberSubscriptionRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String NameSubscription;
    @NotNull
    private String subscriptionPlan;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
}