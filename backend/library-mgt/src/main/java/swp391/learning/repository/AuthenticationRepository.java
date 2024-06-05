package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.EnumUserStatus;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findUserById(int id);

    Optional<User> findByUsernameAndStatus(String username, EnumUserStatus status);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndStatus(String email, EnumUserStatus status);

    @Query(value = "SELECT COUNT(*) FROM users WHERE deleted = false", nativeQuery = true)
    int getTotalUser();

}
