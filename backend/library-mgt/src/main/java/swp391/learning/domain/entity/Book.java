package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String desc;
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Category category;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "link_thummail")
    private String linkThumnail;
    @Column(name="price")
    private double price;
    @Column(name="deleted")
    private boolean isDeleted;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn (name="created_by",referencedColumnName = "id")
    private User userCreated;
    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;
}
