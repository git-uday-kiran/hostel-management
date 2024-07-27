package management.hostel.models.identity;

import management.hostel.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

	private String address;

	@Column(length = 45)
	private String district;

	@ManyToOne
	private City city;

	@ManyToOne
	private State state;

	@ManyToOne
	private Country country;

	@Column(nullable = false)
	@Pattern(regexp = "\\d{6}", message = "pincode should be six digits")
	private String pincode;

}
