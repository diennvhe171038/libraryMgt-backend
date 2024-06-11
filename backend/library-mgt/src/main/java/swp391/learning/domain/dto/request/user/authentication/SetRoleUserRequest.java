package swp391.learning.domain.dto.request.user.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import swp391.learning.domain.enums.EnumTypeRole;

@Data
public class SetRoleUserRequest {
    @NotBlank
    private String username;
    @NotNull
    private EnumTypeRole typeRole;
}