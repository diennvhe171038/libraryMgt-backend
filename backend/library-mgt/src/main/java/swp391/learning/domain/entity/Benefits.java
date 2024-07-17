package swp391.learning.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "benefits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Benefits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_rent")
    private int numberOfRent;

    @OneToMany(mappedBy = "benefits")
    Set<MemberBenefit> memberBenefits;
}