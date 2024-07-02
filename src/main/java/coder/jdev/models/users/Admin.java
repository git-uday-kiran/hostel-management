package coder.jdev.models.users;

import coder.jdev.models.hostel.Hostel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	private long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime expiration;

	@JoinColumn(nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private Hostel hostel;

}

