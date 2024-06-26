package swp391.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.learning.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> getOrderById(int id);
}