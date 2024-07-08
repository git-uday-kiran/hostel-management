package management.hostel.models.identity;

import management.hostel.models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cities")
//@Subselect("select * from cities")
public class City extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	private State state;

	@Column(nullable = false)
	private String stateCode;

	@ManyToOne
	private Country country;

	@Column(nullable = false)
	private String countryCode;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;

	@Column
	private Byte flag;

	private String wikiDataId;

	@Override
	public void prePersist() {
		super.prePersist();
		flag = 1;
	}

}
