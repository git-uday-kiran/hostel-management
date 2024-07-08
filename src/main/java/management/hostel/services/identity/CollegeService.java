package management.hostel.services.identity;

import management.hostel.dto.response.TemplateResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.exceptions.identity.CollegeException;
import management.hostel.exceptions.identity.CountryException;
import management.hostel.models.identity.College;
import management.hostel.repositories.identity.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

	private final CollegeRepository repository;

	public College findById(long id) throws CountryException {
		return repository.findById(id).orElseThrow(() -> new CollegeException("College with id: %d does not exist.".formatted(id)));
	}

	public List<CollegeResponse> findAll(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public List<TemplateResponse> getCollegesByName(final String collegeName, final Pageable pageable) {
		List<College> colleges = repository.findAllByCollegeNameLikeIgnoreCase("%" + collegeName + "%", pageable);
		return colleges.stream()
			.map(college -> TemplateResponse.builder()
				.id(college.getId())
				.name(college.getCollegeName())
				.district(college.getDistrictName())
				.build())
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
