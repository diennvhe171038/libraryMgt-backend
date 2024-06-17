package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="deleted")
    private boolean isDeleted;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="describe")
    private String describe; //gioi thieu tac gia
    @Column(name = "link_thummail")
    private String link_Thumnail; // anh tac gia
    @ManyToOne
    @JoinColumn (name="created_by",referencedColumnName = "id")
    private User userCreated;
    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;
}
