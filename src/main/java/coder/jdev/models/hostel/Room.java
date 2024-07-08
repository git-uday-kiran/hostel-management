package coder.jdev.models.hostel;

import coder.jdev.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {

	@Column(nullable = false)
	private String roomNo;

	@Column(scale = 99)
	private int floorNo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Hostel hostel;

}
