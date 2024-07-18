package swp391.learning.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.EnumTypeRole;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findById(int id);

    @Query("SELECT u FROM User u WHERE u.role = :role1 OR u.role = :role2")
    List<User> findAllByLibrarianOrMemberRoles(EnumTypeRole role1, EnumTypeRole role2);
}
