package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import swp391.learning.domain.enums.EnumLoanStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "member_id")
    private User user;

    @Column(name = "borrow_at")
    private LocalDateTime borrowAt; // thời gian mượn

    @Column(name = "return_at")
    private LocalDateTime returnAt; // thời gian  trả

    @Column(name = "due_date")
    private LocalDateTime dueDate; // thời hạn hết hạn trả sách

    @Column(name = "status")
    private EnumLoanStatus status; // trạng thái mượn sách

    @Column(name = "note")
    private String note; // ghi chú

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
