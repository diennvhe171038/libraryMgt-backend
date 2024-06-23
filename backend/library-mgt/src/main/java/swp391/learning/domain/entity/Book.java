package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(name = "nameBook")
    private String nameBook;

    @Column(name = "description")
    private String desc; // gioi thieu sach

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name="price")
    private double price;

    @Column(name="deleted")
    private boolean isDeleted;

    @Column(name="stock")
    private int stock; // so luong sach

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn (name="created_by",referencedColumnName = "id")
    private User userCreated;

    @ManyToOne
    @JoinColumn(name="updated_by",referencedColumnName = "id")
    private User userUpdated;
}
