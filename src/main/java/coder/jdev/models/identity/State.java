package coder.jdev.models.identity;

import coder.jdev.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "states")
//@Subselect("select * from states")
public class State extends BaseEntity {

	private String name;

	@ManyToOne
	private Country country;

	@Column(length = 2)
	private String countryCode;

	private String fipsCode;

	private String iso2;

	@Column(length = 191)
	private String type;

	@Column
	private Double latitude;

	@Column
	private Double longitude;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	@Column
	private Byte flag;

	private String wikiDataId;

	@Override
	public void prePersist() {
		super.prePersist();
		flag = 1;
	}

}
