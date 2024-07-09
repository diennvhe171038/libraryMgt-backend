package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.enums.EnumMembershipType;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_subscription")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberSubscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nameSubscription")
    private String nameSubscription;
    @Column(name = "subscription_plan")
    private String subscriptionPlan;
    @Column(name="fee_member")
    private double feeMember;
    private boolean isDeleted = false;
    @Column(name="membership")
    private EnumMembershipType membershipType;

    @Column(name = "start_date")
    private LocalDateTime startDate; 

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userCreated;
    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;
}
