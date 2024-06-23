package swp391.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "author")
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="deleted")
    private boolean isDeleted;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="description")
    private String desc; //gioi thieu tac gia
    @Column(name = "link_thummail")
    private String link_Thumnail; // anh tac gia

    @ManyToOne
    @JoinColumn (name="created_by",referencedColumnName = "id")
    private User userCreated;

    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Book> books;
}
