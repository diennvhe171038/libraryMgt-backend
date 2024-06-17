package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.learning.domain.enums.EnumPaymentGateway;
import swp391.learning.domain.enums.EnumPaymentProcess;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_gateway")
    private EnumPaymentGateway paymentGateway;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumPaymentProcess status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
