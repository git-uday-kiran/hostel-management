package coder.jdev.models.identity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Subselect("select * from college")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 400, nullable = false)
    private String collegeName;

    @Column(length = 400, nullable = false)
    private String universityName;

    @Column(length = 50, nullable = true)
    private String collegeType;

    @Column(length = 50, nullable = false)
    private String stateName;

    @Column(length = 50, nullable = false)
    private String districtName;
}
