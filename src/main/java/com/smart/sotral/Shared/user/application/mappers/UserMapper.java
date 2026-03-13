package com.smart.sotral.Shared.user.application.mappers;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smart.sotral.Shared.user.application.dtos.requests.UserRequest;
import com.smart.sotral.Shared.user.application.dtos.responses.UserResponse;
import com.smart.sotral.Shared.user.domain.enums.TypeRole;
import com.smart.sotral.Shared.user.domain.models.User;
import com.smart.sotral.Shared.user.domain.models.Admin;
import com.smart.sotral.Shared.user.domain.models.Usager;


@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Admin toAdmin(UserRequest request){
        if (request == null) {
            throw new IllegalArgumentException("UserRequest ne peut pas être null");
        }
        Admin admin = new Admin();
        applyCommonFields(admin, request);
        admin.setRole(TypeRole.ADMIN);
        return admin;
    }

    public Usager toUsager(UserRequest request){
        if (request == null) {
            throw new IllegalArgumentException("UserRequest ne peut pas être null");
        }
        Usager usager = new Usager();
        applyCommonFields(usager, request);
        usager.setRole(TypeRole.USAGER);
        return usager;
    }

    private void applyCommonFields(User user, UserRequest request) {
        if (user.getTrackingId() == null) {
            user.setTrackingId(UUID.randomUUID());
        }
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setActive(true);
    }

    public UserResponse toResponse(User user){
        if (user == null) {
            throw new IllegalArgumentException("User ne peut pas être null");
        }
        
        return new UserResponse(
                user.getTrackingId(),
                user.getFirstName(),
                user.getLastName(),
                null, // phone removed
                user.getEmail(),
                user.getRole().name(),
                null, // country removed
                user.isActive()
        );
    }
}
