package management.hostel.models.users;

import management.hostel.models.hostel.Room;
import management.hostel.models.identity.College;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	private Long id;

	@MapsId
	@ManyToOne
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	private College college;

	//	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate joiningDate;

	@Temporal(TemporalType.DATE)
	private LocalDate leaveDate;

}

