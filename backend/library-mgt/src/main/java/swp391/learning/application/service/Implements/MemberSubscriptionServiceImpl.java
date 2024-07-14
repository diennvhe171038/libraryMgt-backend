package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.MemberSubscriptionService;
import swp391.learning.application.service.PaymentService;
import swp391.learning.config.VnPayConfig;
import swp391.learning.domain.dto.common.PaymentRes;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.membership.AddMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.DeleteMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.EnrollMembershipRequest;
import swp391.learning.domain.dto.request.admin.membership.UpdateMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.user.book.PaymentConfirmRequest;
import swp391.learning.domain.dto.response.admin.membership.AddMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.DeleteMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.UpdateMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.user.membership.EnrollMembershipResponse;
import swp391.learning.domain.dto.response.user.membership.PaymentConfirmResponse;
import swp391.learning.domain.entity.MemberSubscription;
import swp391.learning.domain.entity.Order;
import swp391.learning.domain.entity.Payment;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.*;
import swp391.learning.repository.MemberSubscriptionRepository;
import swp391.learning.repository.OrderRepository;
import swp391.learning.repository.PaymentRepository;
import swp391.learning.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberSubscriptionServiceImpl implements MemberSubscriptionService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final MemberSubscriptionRepository memberSubscriptionRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberSubscriptionServiceImpl.class);

    @Override
    public ResponseCommon<List<MemberSubscription>> getAllMemberships(){
        List<MemberSubscription> memberSubscription = memberSubscriptionRepository.findAll();
        log.debug("Get all membership with " + memberSubscription.size() + " records");
        return new ResponseCommon<>(ResponseCode.SUCCESS, memberSubscription);
    }


    @Override
    public ResponseCommon<AddMemberSubscriptionResponse> addMemberSubscription(AddMemberSubscriptionRequest addMemberSubscriptionRequest) {
        try {
            MemberSubscription memberSubscription = memberSubscriptionRepository.findMemberSubscriptionByNameSubscription(addMemberSubscriptionRequest.getNameSubscription()).orElse(null);
            User user = userRepository.findByEmail(addMemberSubscriptionRequest.getEmail());
            if (Objects.isNull(user)) {
                log.debug("Add MemberSubscription failed: User does not exist");
                return new ResponseCommon<>(ResponseCode.SUBSCRIPTION_EXIST, null);
            }
            if (Objects.isNull(memberSubscription)) {
                memberSubscription = new MemberSubscription();
            }
            memberSubscription.setUserCreated(user);
            memberSubscription.setDeleted(false);
            memberSubscription.setNameSubscription(addMemberSubscriptionRequest.getNameSubscription());
//            memberSubscription.setSubscriptionPlan(addMemberSubscriptionRequest.getSubscriptionPlan());
            memberSubscription.setFeeMember(addMemberSubscriptionRequest.getFee_member());
            memberSubscription.setStartDate(addMemberSubscriptionRequest.getStartDate());
//            memberSubscription.setMembershipType(addMemberSubscriptionRequest.getMembershipType());
            memberSubscription.setEndDate(addMemberSubscriptionRequest.getEndDate());
            memberSubscription.setCreatedAt(LocalDateTime.now());

            MemberSubscription savedSubscription = memberSubscriptionRepository.save(memberSubscription);
            if (savedSubscription == null) {
                log.debug("Add Member Subscription failed: Unable to save the subscription");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }

            AddMemberSubscriptionResponse response = new AddMemberSubscriptionResponse("Add Member Subscription successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Add Member Subscription failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<UpdateMemberSubscriptionResponse> updateMemberSubscription(UpdateMemberSubscriptionRequest updateMemberSubscriptionRequest) {
        try {
            MemberSubscription memberSubscription = memberSubscriptionRepository.findById(updateMemberSubscriptionRequest.getId()).orElse(null);
            User user = userRepository.findByEmail(updateMemberSubscriptionRequest.getEmail());

            if (Objects.isNull(memberSubscription)) {
                log.debug("Update MemberSubscription failed: Subscription does not exist");
                return new ResponseCommon<>(ResponseCode.SUBSCRIPTION_NOT_EXIST, null);
            }
            MemberSubscription memberSubscriptionUpdate = memberSubscription;
            memberSubscriptionUpdate.setUserCreated(user);
            memberSubscription.setDeleted(updateMemberSubscriptionRequest.isDeleted());
            memberSubscription.setNameSubscription(updateMemberSubscriptionRequest.getNameSubscription());
//            memberSubscriptionUpdate.setSubscriptionPlan(updateMemberSubscriptionRequest.getSubscriptionPlan());
            memberSubscriptionUpdate.setFeeMember(updateMemberSubscriptionRequest.getFee_member());
//            memberSubscriptionUpdate.setMembershipType(updateMemberSubscriptionRequest.getMembershipType());
            memberSubscriptionUpdate.setStartDate(updateMemberSubscriptionRequest.getStartDate());
            memberSubscriptionUpdate.setEndDate(updateMemberSubscriptionRequest.getEndDate());
            memberSubscriptionUpdate.setCreatedAt(LocalDateTime.now());

            MemberSubscription updatedSubscription = memberSubscriptionRepository.save(memberSubscription);
            if (updatedSubscription == null) {
                log.debug("Update Member Subscription failed: Unable to update the subscription");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }

            UpdateMemberSubscriptionResponse response = new UpdateMemberSubscriptionResponse("Update Member Subscription successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Update Member Subscription failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
    @Override
    public ResponseCommon<DeleteMemberSubscriptionResponse> deleteMemberSubscription(DeleteMemberSubscriptionRequest deleteMemberSubscriptionRequest) {
        try {
            MemberSubscription memberSubscription = memberSubscriptionRepository.findMemberSubscriptionById(deleteMemberSubscriptionRequest.getSubscriptionID()).orElse(null);
            User user = userRepository.findByEmail(deleteMemberSubscriptionRequest.getEmail());
            // if Member is null -> tell the user
            if (Objects.isNull(memberSubscription)) {
                log.debug("Delete Member Subscription failed: Member Subscription does not exist");
                return new ResponseCommon<>(ResponseCode.SUBSCRIPTION_NOT_EXIST, null);
            } else {
                MemberSubscription memberSubscriptionUpdate = memberSubscription;
                memberSubscriptionUpdate.setDeleted(true);
                memberSubscriptionUpdate.setUserCreated(user);
                memberSubscriptionRepository.save(memberSubscriptionUpdate);

                DeleteMemberSubscriptionResponse deleteMemberSubscriptionResponse = new DeleteMemberSubscriptionResponse();
                deleteMemberSubscriptionResponse.setMessage("Delete Member Subscription successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, deleteMemberSubscriptionResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Delete Member Subscription failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<EnrollMembershipResponse> enrollMembership(EnrollMembershipRequest enrollCourseRequest) {
        try {
            EnrollMembershipResponse enrollMembershipResponse = new EnrollMembershipResponse();
            MemberSubscription memberSubscription = memberSubscriptionRepository.findMemberSubscriptionById(enrollCourseRequest.getMembershipId()).orElse(null);
            System.out.println(memberSubscription);
            Order order = new Order();
            order.setCreated_at(LocalDateTime.now());
            order.setUser(userRepository.findByEmail(enrollCourseRequest.getEmail()));
            order.setMemberSubscription(memberSubscription);
            order.setEnumTypeProcessPayment(EnumTypeProcessPayment.INPROCESS);
            order.setAmount(memberSubscription.getFeeMember());
            orderRepository.save(order);

            ResponseCommon<PaymentRes> paymentResponse = paymentService.addPayment(memberSubscription.getFeeMember());
            if (paymentResponse.getCode() == ResponseCode.SUCCESS.getCode()) {
                enrollMembershipResponse.setOrderId(order.getId());
                enrollMembershipResponse.setUrlPayment(paymentResponse.getData().getUrl());
                order.setChecksum(paymentResponse.getData().getVnp_TxnRef());
                orderRepository.save(order);
                return new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Send url done", enrollMembershipResponse);
            } else {
                log.debug("Enroll course response faile because paymentResponse not success.");
                return new ResponseCommon<>(ResponseCode.SEND_URL_PAYMENT_FAIL.getCode(), "Send url fail", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Enroll Course failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }

    @Override
    public ResponseCommon<PaymentConfirmResponse> paymentConfirm(PaymentConfirmRequest paymentConfirmRequest) {
        try {
            Order order = orderRepository.getOrderById(paymentConfirmRequest.getOrderId()).orElse(null);
            if (Objects.isNull(order)) {
                return new ResponseCommon<>(ResponseCode.ORDER_NOT_EXIST, null);
            }
            String signValue = generateAndHashQueryString(paymentConfirmRequest);
            System.out.println(signValue);
            String vnp_SecureHash = paymentConfirmRequest.getVnp_SecureHash();
            System.out.println(vnp_SecureHash);
            String checksum = order.getChecksum();
            String vnp_TnxRef = paymentConfirmRequest.getVnp_TxnRef();
            System.out.println(checksum);
            System.out.println(vnp_TnxRef);
            double amountDB = order.getAmount();
            double amountReturn = Double.parseDouble(paymentConfirmRequest.getVnp_Amount());


            if (vnp_SecureHash.isEmpty()) {
                log.debug("Handle with vnp_secureHash: " + vnp_SecureHash);
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            } else if (!signValue.equals(vnp_SecureHash)) {
                return new ResponseCommon<>(ResponseCode.CHANGE_PARAM.getCode(), "Param is hacker", null);
            } else if (!vnp_TnxRef.equals(checksum)) {
                return new ResponseCommon<>(ResponseCode.ORDER_NOT_FOUND.getCode(), "Order not found", null);
            } else if (amountReturn == amountDB) {
                return new ResponseCommon<>(ResponseCode.INVALID_AMOUNT.getCode(), "Invalid Amount", null);
            } else if (!order.getEnumTypeProcessPayment().equals(EnumTypeProcessPayment.INPROCESS)) {
                return new ResponseCommon<>(ResponseCode.ORDER_ALREADY_CONFIRM.getCode(), "Order already confirm", null);
            } else if (!paymentConfirmRequest.getVnp_ResponseCode().equals("00")) {
                return new ResponseCommon<>(ResponseCode.USER_CANCEL_BILL.getCode(), "User cancel bill", null);
            }

            // Create the payment only once
            Payment payment = new Payment();
            payment.setUser(order.getUser());
            payment.setMemberSubscription(order.getMemberSubscription());
            payment.setPaymentGateway(EnumPaymentGateway.VN_PAY);
            payment.setAmount(amountReturn);
            payment.setEnumPaymentProcess(EnumPaymentProcess.SUCCESS);
            payment.setTransactionId(order.getChecksum());
            payment.setCreatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            // Update order
            order.setPayment(payment);
            order.setEnumTypeProcessPayment(EnumTypeProcessPayment.DONE);
            orderRepository.save(order);

            return new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Confirm success", null);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Enroll Course failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
    public String generateAndHashQueryString(PaymentConfirmRequest paymentConfirmRequest) {
        Map<String, String> fields = new HashMap<>();
        fields.put("vnp_Amount", paymentConfirmRequest.getVnp_Amount());
        fields.put("vnp_BankCode", paymentConfirmRequest.getVnp_BankCode());
        fields.put("vnp_BankTranNo", paymentConfirmRequest.getVnp_BankTranNo());
        fields.put("vnp_CardType", paymentConfirmRequest.getVnp_CardType());
        fields.put("vnp_OrderInfo", paymentConfirmRequest.getVnp_OrderInfo());
        fields.put("vnp_PayDate", paymentConfirmRequest.getVnp_PayDate());
        fields.put("vnp_ResponseCode", paymentConfirmRequest.getVnp_ResponseCode());
        fields.put("vnp_TmnCode", paymentConfirmRequest.getVnp_TmnCode());
        fields.put("vnp_TransactionNo", paymentConfirmRequest.getVnp_TransactionNo());
        fields.put("vnp_TransactionStatus", paymentConfirmRequest.getVnp_TransactionStatus());
        fields.put("vnp_TxnRef", paymentConfirmRequest.getVnp_TxnRef());

        String queryString = VnPayConfig.hashAllFields(fields);

        return queryString;
    }


    @Override
    public MemberSubscription findById(int id) {
        return memberSubscriptionRepository.findMemberSubscriptionById(id).orElse(null);
    }
}
