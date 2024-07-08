package management.hostel.models.hostel;

import management.hostel.models.users.Staff;
import management.hostel.models.users.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

	@Id
	@Temporal(TemporalType.DATE)
	private LocalDate date;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> students;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Staff> staff;
}

