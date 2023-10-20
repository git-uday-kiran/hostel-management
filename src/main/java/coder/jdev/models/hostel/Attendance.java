package coder.jdev.models.hostel;

import coder.jdev.models.users.Staff;
import coder.jdev.models.users.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Staff> staff;
}

