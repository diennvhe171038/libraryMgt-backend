package swp391.learning.domain.dto.request.user.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetUserByEmailRequest {
    @NotBlank
    private String email;
}
