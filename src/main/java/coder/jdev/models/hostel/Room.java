package coder.jdev.models.hostel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints =
        @UniqueConstraint(name = "room_floor_hostel",
                columnNames = {"room_no", "floor_no", "hostel_id"}
        ))
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_no")
    private String roomNo;

    @Column(scale = 99, name = "floor_no")
    private int floorNo;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Hostel hostel;

}
