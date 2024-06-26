package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_subscription")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userCreated;
    @Column(name = "nameSubscription")
    private String nameSubscription;
    @Column(name = "subscription_plan")
    private String subscriptionPlan;
    @Column(name="fee_member")
    private double feeMember;
    private boolean isDeleted;
    @Column(name = "start_date")
    private LocalDateTime startDate; 

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
