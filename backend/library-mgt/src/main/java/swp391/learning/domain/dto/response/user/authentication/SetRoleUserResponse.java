package swp391.learning.domain.dto.response.user.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import swp391.learning.domain.enums.EnumTypeRole;

@Data
public class SetRoleUserResponse {
    @NotBlank
    private String username;
    @NotNull
    private EnumTypeRole typeRole;
}
