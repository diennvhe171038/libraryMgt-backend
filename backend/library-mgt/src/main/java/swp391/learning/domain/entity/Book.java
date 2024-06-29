package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

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

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> category;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // thoi gian tao

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "price")
    private double price; // gia quyen sach ben ngoai

    @Column(name = "deleted")
    private boolean isDeleted;

    @Column(name = "stock")
    private int stock; // so luong sach

    @Column(name = "ISBN")
    private String ISBN; // ma quyen sach

    @Column(name = "total_page")
    private String totalPage;

    @Column(name = "language")
    private String language;

    @Column(name = "publisher")
    private String publisher; // nha xuat ban

    @Column(name = "publication_year")
    private String publicationYear; // nam xuat ban

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // thời gian cap nhat

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User userCreated; // nguoi tạo

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User userUpdated; // nguoi sua
}
