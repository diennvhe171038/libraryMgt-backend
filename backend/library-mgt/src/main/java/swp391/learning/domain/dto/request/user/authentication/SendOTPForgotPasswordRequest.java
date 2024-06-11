package swp391.learning.domain.dto.request.user.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendOTPForgotPasswordRequest {
    @NotBlank
    private String email;

}
