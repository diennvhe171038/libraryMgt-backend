package swp391.learning.application.service;

import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.membership.AddMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.DeleteMemberSubscriptionRequest;
import swp391.learning.domain.dto.request.admin.membership.UpdateMemberSubscriptionRequest;
import swp391.learning.domain.dto.response.admin.membership.AddMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.DeleteMemberSubscriptionResponse;
import swp391.learning.domain.dto.response.admin.membership.UpdateMemberSubscriptionResponse;


public interface MemberSubscriptionService {
    ResponseCommon<AddMemberSubscriptionResponse> addMemberSubscription(AddMemberSubscriptionRequest request);
    ResponseCommon<UpdateMemberSubscriptionResponse> updateMemberSubscription(UpdateMemberSubscriptionRequest request);

    ResponseCommon<DeleteMemberSubscriptionResponse> deleteMemberSubscription(DeleteMemberSubscriptionRequest deleteMemberSubscriptionRequest);
}
