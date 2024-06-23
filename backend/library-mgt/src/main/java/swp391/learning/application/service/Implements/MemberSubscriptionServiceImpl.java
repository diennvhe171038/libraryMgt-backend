package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swp391.learning.application.service.MemberSubscriptionService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.membership.AddMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.DeleteMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.UpdateMemberSubscriptionRequest;
import swp391.learning.domain.dto.response.admin.membership.AddMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.DeleteMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.UpdateMemberSubscriptionResponse;
import swp391.learning.domain.entity.MemberSubscription;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.repository.MemberSubscriptionRepository;
import swp391.learning.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberSubscriptionServiceImpl implements MemberSubscriptionService {
    private final UserRepository userRepository;
    private final MemberSubscriptionRepository memberSubscriptionRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberSubscriptionServiceImpl.class);

    @Override
    public ResponseCommon<AddMemberSubscriptionResponse> addMemberSubscription(AddMemberSubscriptionRequest addMemberSubscriptionRequest) {
        try {
            MemberSubscription memberSubscription = memberSubscriptionRepository.findMemberSubscriptionByNameSubscription(addMemberSubscriptionRequest.getNameSubscription()).orElse(null);
            User user = userRepository.findByEmail(addMemberSubscriptionRequest.getEmail());
            if (Objects.isNull(user)) {
                log.debug("Add MemberSubscription failed: User does not exist");
                return new ResponseCommon<>(ResponseCode.SUBSCRIPTION_NOT_EXIST, null);
            }
            if (Objects.isNull(memberSubscription)) {
                memberSubscription = new MemberSubscription();
            }
            memberSubscription.setUserCreated(user);
            memberSubscription.setDeleted(false);
            memberSubscription.setNameSubscription(addMemberSubscriptionRequest.getNameSubscription());
            memberSubscription.setSubscriptionPlan(addMemberSubscriptionRequest.getSubscriptionPlan());
            memberSubscription.setStartDate(addMemberSubscriptionRequest.getStartDate());
            memberSubscription.setEndDate(addMemberSubscriptionRequest.getEndDate());
            memberSubscription.setCreatedAt(LocalDateTime.now());

            MemberSubscription savedSubscription = memberSubscriptionRepository.save(memberSubscription);
            if (savedSubscription == null) {
                log.debug("Add MemberSubscription failed: Unable to save the subscription");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }

            AddMemberSubscriptionResponse response = new AddMemberSubscriptionResponse("Add MemberSubscription successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Add MemberSubscription failed: " + e.getMessage());
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
            memberSubscriptionUpdate.setSubscriptionPlan(updateMemberSubscriptionRequest.getSubscriptionPlan());
            memberSubscriptionUpdate.setStartDate(updateMemberSubscriptionRequest.getStartDate());
            memberSubscriptionUpdate.setEndDate(updateMemberSubscriptionRequest.getEndDate());
            memberSubscriptionUpdate.setCreatedAt(LocalDateTime.now());

            MemberSubscription updatedSubscription = memberSubscriptionRepository.save(memberSubscription);
            if (updatedSubscription == null) {
                log.debug("Update MemberSubscription failed: Unable to update the subscription");
                return new ResponseCommon<>(ResponseCode.FAIL, null);
            }

            UpdateMemberSubscriptionResponse response = new UpdateMemberSubscriptionResponse("Update MemberSubscription successful");
            return new ResponseCommon<>(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Update MemberSubscription failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
    @Override
    public ResponseCommon<DeleteMemberSubscriptionResponse> deleteMemberSubscription(DeleteMemberSubscriptionRequest deleteMemberSubscriptionRequest) {
        try {
            MemberSubscription memberSubscription = memberSubscriptionRepository.findMemberSubscriptionById(deleteMemberSubscriptionRequest.getSubscriptionID()).orElse(null);
            User user = userRepository.findByEmail(deleteMemberSubscriptionRequest.getEmail());
            // if category is null -> tell the user
            if (Objects.isNull(memberSubscription)) {
                log.debug("Delete Category failed: Category does not exist");
                return new ResponseCommon<>(ResponseCode.CATEGORY_NOT_EXIST, null);
            } else {
                MemberSubscription memberSubscriptionUpdate = memberSubscription;
                memberSubscriptionUpdate.setDeleted(true);
                memberSubscriptionUpdate.setUserCreated(user);
                memberSubscriptionRepository.save(memberSubscriptionUpdate);

                DeleteMemberSubscriptionResponse deleteMemberSubscriptionResponse = new DeleteMemberSubscriptionResponse();
                deleteMemberSubscriptionResponse.setMessage("Delete Category successful");
                return new ResponseCommon<>(ResponseCode.SUCCESS, deleteMemberSubscriptionResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Delete Category failed: " + e.getMessage());
            return new ResponseCommon<>(ResponseCode.FAIL, null);
        }
    }
}
