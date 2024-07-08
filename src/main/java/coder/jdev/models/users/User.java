package coder.jdev.models.users;

import coder.jdev.models.BaseEntity;
import coder.jdev.models.identity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public LocalDate dateOfBirth;

	@Pattern(regexp = "[\\w\\.]{0,30}", message = "username is not valid, only allowed small letters and dot's")
	@Column(length = 30, nullable = false, unique = true)
	protected String username;

	@Email
	@Column(length = 100, unique = true, nullable = false)
	protected String email;

	@Column(nullable = false)
	@Pattern(regexp = "(\\+91|91)?\\d{10}", message = "doesn't seem to be a valid mobile number")
	protected String mobile;

	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	protected Address address;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	protected Gender gender;

}
