package coder.jdev.models.hostel;

import coder.jdev.models.BaseEntity;
import coder.jdev.models.identity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hostel extends BaseEntity {

	@Size(min = 5, max = 50, message = "hostel name must be in the range of 5 to 50")
	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Address address;

	@Email
	@Column(length = 100, unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	@Pattern(regexp = "(\\+91|91)\\d{10}", message = "doesn't seem to be a valid mobile number")
	private String mobile;

	@OneToMany(mappedBy = "hostel")
	private List<Room> rooms;

}

