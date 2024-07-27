package management.hostel.services.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.exceptions.identity.CountryException;
import management.hostel.models.identity.College;
import management.hostel.repositories.identity.CollegeRepository;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

	private final CollegeRepository repository;

	public College getCollegeById(long id) throws CountryException {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("College does not exist with id %d".formatted(id)));
	}

	public CollegeResponse getCollegeResponseById(long id) {
		return responseOf(getCollegeById(id));
	}

	public List<CollegeResponse> findAll(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public List<MapResponse> getCollegesByName(final String collegeName, final Pageable pageable) {
		List<College> colleges = repository.findAllByCollegeNameLikeIgnoreCase("%" + collegeName + "%", pageable);
		return colleges.stream()
			.map(college -> MapResponse.create()
				.set("id", college.getId())
				.set("name", college.getCollegeName())
				.set("district", college.getDistrictName())
				.set("state", college.getStateName())
				.set("university", college.getUniversityName())
				.set("college_type", college.getCollegeType()))
			.toList();
	}

	public List<CollegeResponse> toResponseList(List<College> colleges) {
		return colleges.stream()
			.map(this::responseOf)
			.toList();
	}

	public College fetchById(final long collegeId) {
		return repository.fetchById(collegeId);
	}

	public CollegeResponse responseOf(College college) {
		return CollegeResponse.builder()
			.id(college.getId())
			.collegeName(college.getCollegeName())
			.universityName(college.getUniversityName())
			.collegeType(college.getCollegeType())
			.stateName(college.getStateName())
			.districtName(college.getDistrictName())
			.build();
	}
}
