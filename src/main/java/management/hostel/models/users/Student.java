package management.hostel.models.users;

import jakarta.persistence.*;
import lombok.*;
import management.hostel.models.BaseEntity;
import management.hostel.models.identity.College;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {

	@Id
	private Long id;

	@MapsId
	@ManyToOne
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private College college;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate joiningDate;

	@Temporal(TemporalType.DATE)
	private LocalDate leaveDate;

}

