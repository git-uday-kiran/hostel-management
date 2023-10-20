package coder.jdev.models.users;

import coder.jdev.models.hostel.Room;
import coder.jdev.models.identity.College;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)

    private long id;

    @MapsId
    @JoinColumn(unique = true, nullable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private College college;

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
        log.info("updating student last updated to {}", LocalDateTime.now());
        lastUpdated = LocalDateTime.now();
    }
}
