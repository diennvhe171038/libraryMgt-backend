package swp391.learning.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.learning.application.service.MemberSubscriptionService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.membership.AddMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.DeleteMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.EnrollMembershipRequest;
import swp391.learning.domain.dto.request.admin.membership.UpdateMemberSubscriptionRequest;
import swp391.learning.domain.dto.response.admin.membership.AddMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.DeleteMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.UpdateMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.user.membership.EnrollMembershipResponse;
import swp391.learning.domain.enums.ResponseCode;

@RestController
@RequestMapping("/api/v1/membership")
@AllArgsConstructor
public class MemberSubController {

    private final MemberSubscriptionService memberSubscriptionService;

    @PostMapping("/add-subscription")
    public ResponseEntity<ResponseCommon<AddMemberSubscriptionResponse>> addMemberSubscription(
            @Valid @RequestBody AddMemberSubscriptionRequest addMemberSubscriptionRequest) {
        ResponseCommon<AddMemberSubscriptionResponse> response = memberSubscriptionService.addMemberSubscription(addMemberSubscriptionRequest);
        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            return ResponseEntity.ok(response);
        } else if (response.getCode() == ResponseCode.SUBSCRIPTION_NOT_EXIST.getCode()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseCommon<>(response.getCode(), "Subscription already exists", null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Add subscription failed", null));
        }
    }

    @PutMapping("/update-subscription")
    public ResponseEntity<ResponseCommon<UpdateMemberSubscriptionResponse>> updateMemberSubscription(
            @Valid @RequestBody UpdateMemberSubscriptionRequest updateMemberSubscriptionRequest) {
        ResponseCommon<UpdateMemberSubscriptionResponse> response = memberSubscriptionService.updateMemberSubscription(updateMemberSubscriptionRequest);
        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            return ResponseEntity.ok(response);
        } else if (response.getCode() == ResponseCode.SUBSCRIPTION_NOT_EXIST.getCode()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseCommon<>(response.getCode(), "Subscription does not exist", null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Update subscription failed", null));
        }
    }

    @DeleteMapping("/delete-subscription")
    public ResponseEntity<ResponseCommon<DeleteMemberSubscriptionResponse>> deleteMemberSubscription(
            @Valid @RequestBody DeleteMemberSubscriptionRequest deleteMemberSubscriptionRequest) {
        ResponseCommon<DeleteMemberSubscriptionResponse> response = memberSubscriptionService.deleteMemberSubscription(deleteMemberSubscriptionRequest);
        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            return ResponseEntity.ok(response);
        } else if (response.getCode() == ResponseCode.CATEGORY_NOT_EXIST.getCode()) { // Check if this code matches your delete subscription scenario
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseCommon<>(response.getCode(), "Subscription does not exist", null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Delete subscription failed", null));
        }
    }
    @PostMapping("/enroll-membership")
    public ResponseEntity<ResponseCommon<EnrollMembershipResponse>> enrollCourse(@Valid @RequestBody EnrollMembershipRequest enrollMembershipRequest){
        ResponseCommon<EnrollMembershipResponse> response = memberSubscriptionService.enrollMembership(enrollMembershipRequest);
        if(response.getCode() == ResponseCode.SEND_URL_PAYMENT_FAIL.getCode()){
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.SEND_URL_PAYMENT_FAIL.getCode(),"Send url payment fail",null));
        } else if(response.getCode()==ResponseCode.FAIL.getCode()){
            return ResponseEntity.badRequest().body(new ResponseCommon<>(ResponseCode.FAIL.getCode(),"Exception send url",null));
        } else {
            return ResponseEntity.ok().body(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(),"Send url payment success",response.getData()));
        }
    }
}
