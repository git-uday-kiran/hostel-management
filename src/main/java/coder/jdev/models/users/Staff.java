package coder.jdev.models.users;

import coder.jdev.models.hostel.Hostel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @MapsId
    @JoinColumn(unique = true, nullable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Double salary;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Hostel hostel;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate joiningDate;

    @Temporal(TemporalType.DATE)
    private LocalDate leaveDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdated;

    @PrePersist
    public void onCreate() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    public enum Role {
        MANAGER, WATCH_MAN, HOUSEKEEPING, KITCHEN, LAUNDRY
    }

}
