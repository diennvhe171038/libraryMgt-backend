package swp391.learning.domain.dto.response.user.payment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
//import swp391.learning.domain.dto.response.user.payment.GetPaymentByUserResponse;

import java.util.List;

@Data
public class ResponsePayment {
    @NotEmpty
    List<GetPaymentByUserResponse> listPayment;
}
