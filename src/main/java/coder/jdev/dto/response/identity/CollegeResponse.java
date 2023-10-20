package coder.jdev.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollegeResponse {

    private Long id;

    private String collegeName;

    private String universityName;

    private String collegeType;

    private String stateName;

    private String districtName;

}
