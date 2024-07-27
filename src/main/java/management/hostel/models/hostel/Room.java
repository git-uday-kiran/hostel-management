package management.hostel.models.hostel;

import jakarta.persistence.*;
import lombok.*;
import management.hostel.models.BaseEntity;
import management.hostel.models.users.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity {

	@Column(nullable = false)
	private int roomNo;

	@Column(nullable = false)
	private int floorNo;

	@Column(nullable = false, updatable = false)
	private int capacity = 1;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Hostel hostel;

	@OneToMany
	public List<User> users = new ArrayList<>();

}
