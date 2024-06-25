package swp391.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category")
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameCategory")
    private String nameCategory;
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

    @ManyToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Book> books;
}
