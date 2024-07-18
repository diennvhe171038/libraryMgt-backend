package swp391.learning.application.service;

import swp391.learning.domain.dto.response.admin.benefits.BenefitResponse;

import java.util.List;

public interface BenefitsService {
    // Benefits createBenefit(CreateBenefitRequest createBenefitRequest);

    // Benefits updateBenefit(int benefitsId, UpdateBenefitRequest updateBenefitRequest);

    void deleteBenefit(int id);

    BenefitResponse getBenefitById(int id);
    List<BenefitResponse> getBenefits();
}
