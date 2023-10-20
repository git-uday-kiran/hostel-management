package coder.jdev.dto.request.hostel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceRequest {

    private LocalDate date;

}