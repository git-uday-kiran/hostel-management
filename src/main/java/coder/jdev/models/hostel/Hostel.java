package coder.jdev.models.hostel;

import coder.jdev.models.identity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id", "email"
        })
})
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = "hostel name must be in the range of 5 to 50")
    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @Email
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "(\\+?(91)?\\s{0,2})\\d{10}", message = "doesn't seem to be a valid mobile number")
    private String mobile;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

}

