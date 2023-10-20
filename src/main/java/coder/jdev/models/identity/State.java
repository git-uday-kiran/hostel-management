package coder.jdev.models.identity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "states")
//@Subselect("select * from states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(length = 2)
    private String countryCode;

    @Column(length = 255)
    private String fipsCode;

    @Column(length = 255)
    private String iso2;

    @Column(length = 191)
    private String type;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column
    private Byte flag;

    @Column(length = 255)
    private String wikiDataId;

    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
        flag = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
