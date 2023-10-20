package coder.jdev.models.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cities")
//@Subselect("select * from cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @Column(nullable = false)
    private String stateCode;

    @ManyToOne
    @JoinColumn
    private Country country;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(nullable = false)
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
