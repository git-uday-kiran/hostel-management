package coder.jdev.services.users;

import coder.jdev.dto.request.users.AdminRequest;
import coder.jdev.dto.response.users.AdminResponse;
import coder.jdev.models.users.Admin;
import coder.jdev.repositories.users.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository repository;

    public List<AdminResponse> findAll() {
        return toResponseList(repository.findAll());
    }

    public List<Admin> findAllByExpirationIsGreaterThan(LocalDateTime fromDateTime) {
        return repository.findAllByExpirationIsGreaterThan(fromDateTime);
    }

    public AdminResponse addAdmin(AdminRequest request) {
        Admin admin = modelOf(request);
        repository.saveAndFlush(admin);
        log.info("Admin with userId: {} is created.", admin.getId());
        return responseOf(admin);
    }

    public List<AdminResponse> toResponseList(List<Admin> admins) {
        return admins.stream()
                .map(this::responseOf)
                .toList();
    }

    public AdminResponse responseOf(Admin admin) {
        return AdminResponse.builder()
                .userId(admin.getId())
                .expiration(admin.getExpiration())
                .build();
    }

    public Admin modelOf(AdminRequest request) {
        return Admin.builder()
                .id(request.getUserId())
                .expiration(request.getExpiration())
                .build();
    }

}
