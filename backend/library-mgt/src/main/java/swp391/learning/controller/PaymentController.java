package swp391.learning.controller;

import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.learning.application.service.Implements.PaymentImpl;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.user.payment.GetPaymentByUserRequest;
import swp391.learning.domain.dto.response.user.payment.ResponsePayment;
import swp391.learning.domain.enums.ResponseCode;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentImpl paymentService;

    @GetMapping("/get-payment-user")
    public ResponseEntity<ResponseCommon<ResponsePayment>> getPaymentByUser(@ParameterObject GetPaymentByUserRequest getPaymentByUserRequest) {
        ResponseCommon<ResponsePayment> response = paymentService.getPaymentByUser(getPaymentByUserRequest);

        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }


}