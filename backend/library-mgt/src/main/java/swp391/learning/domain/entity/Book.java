package swp391.learning.domain.entity;

    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.Accessors;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;
    import swp391.learning.domain.enums.EnumBookStatus;

    import java.math.BigDecimal;
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

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String desc; // gioi thieu sach

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SampleBook> sampleBooks;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookCopy> bookCopies;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @Column(name = "imagePath")
    private String imagePath;


    @Column(name="price")
    private BigDecimal price; // gia quyen sach ben ngoai
  
    @Column(name = "price")
    private double price; // gia quyen sach ben ngoai

    @Column(name = "deleted")
    private boolean isDeleted;

    @Column(name = "stock")
    private int stock; // so luong sach


    @Column(name = "ISBN")
    private String ISBN; // ma quyen sach


    @Column(name="total_page")
    private int totalPage;


    @Column(name = "language")
    private String language;

    @Column(name = "publisher")
    private String publisher; // nha xuat ban

    @Column(name="publication_year")
    private int publicationYear; // nam xuat ban

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EnumBookStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt; // thoi gian tao

    @Column(name="updated_at")
    @UpdateTimestamp
    @Column(name = "updated_at")

    private LocalDateTime updatedAt; // thời gian cap nhat

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User userCreated; // nguoi tạo

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User userUpdated; // nguoi sua
}
