package management.hostel.models.hostel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import management.hostel.models.users.User;

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
	private List<User> users;

}

