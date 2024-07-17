package swp391.learning.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MemberBenefitKey implements Serializable{
    @Column(name = "member_id")
    int memberId;

    @Column(name = "benefit_id")
    int benefitId;
}
