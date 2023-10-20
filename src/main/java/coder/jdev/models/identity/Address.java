package coder.jdev.models.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints =
//        {@UniqueConstraint(name = "addressUniqueConstraints", columnNames = {"address", "district", "city_id", "state_id", "country_id", "pincode"})}
//)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String address;

    @Column(length = 45)
    private String district;

    @ManyToOne
    private City city;

    @ManyToOne
    private State state;

    @ManyToOne
    private Country country;

    private Integer pincode;
}
