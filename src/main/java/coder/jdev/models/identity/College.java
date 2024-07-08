package coder.jdev.models.identity;

import coder.jdev.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Subselect("select * from college")
public class College extends BaseEntity {

	@Column(length = 400, nullable = false)
	private String collegeName;

	@Column(length = 400, nullable = false)
	private String universityName;

	@Column(length = 50)
	private String collegeType;

	@Column(length = 50, nullable = false)
	private String stateName;

	@Column(length = 50, nullable = false)
	private String districtName;
}
