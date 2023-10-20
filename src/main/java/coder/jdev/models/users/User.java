package coder.jdev.models.users;

import coder.jdev.models.identity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 30, nullable = false, unique = true)
    protected String username;

    @Column(length = 50, nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, unique = true, updatable = true)
    protected String mobile;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected Address address;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    public LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

}
