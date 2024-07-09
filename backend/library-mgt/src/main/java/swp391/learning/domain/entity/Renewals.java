package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "renewals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Renewals implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "renewed_at")
    private LocalDateTime renewedAt; // thời gian gia hạn gói thành viên

    @Column(name = "return_at")
    private LocalDateTime returnAt; // thời gian hủy gói thành viên

    @Column(name = "new_due_date")
    private LocalDateTime newDueDate; // thời hạn hết hạn gói thành viên

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;





}
