package swp391.learning.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import swp391.learning.domain.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Otp findByEmail(String email);

    void deleteByEmail(String email);
}
