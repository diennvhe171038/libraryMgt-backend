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
import java.util.List;
import java.util.Set;

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

    @Column(name = "name")
    private String name; // tên gói

    @Column(name = "price")
    private BigDecimal price; // giá gói

    @Column(name="max_book")
    private int maxBook; // số lượng mượn sách tối đa

    @Column(name = "loan_duration")
    private int loanDuration; // thời gian mượn sách (số ngày)

    @Column(name = "renew_limit")
    private int renewLimit; // số lần gia hạn tối đa

    @Column(name = "renew_duration")
    private int renewDuration; // thời gian gia hạn (số ngày)

    @OneToMany(mappedBy = "memberSubscription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userCreated;
    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;
}
