package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.learning.domain.entity.MemberSubscription;

import java.util.Optional;

public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Integer> {
    Optional<MemberSubscription> findMemberSubscriptionByNameSubscription(String nameSubscription);
    Optional<MemberSubscription> findMemberSubscriptionById(int id);
}

