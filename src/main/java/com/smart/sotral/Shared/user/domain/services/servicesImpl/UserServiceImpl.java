package com.smart.sotral.Shared.user.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smart.sotral.Shared.jwt.JwtService;
import com.smart.sotral.Shared.mailling.config.MailConstants;
import com.smart.sotral.Shared.mailling.dto.request.EmailRequest;
import com.smart.sotral.Shared.mailling.service.EmailService;
import com.smart.sotral.Shared.user.application.dtos.requests.LoginRequest;
import com.smart.sotral.Shared.user.application.dtos.requests.UserRequest;
import com.smart.sotral.Shared.user.application.dtos.responses.LoginResponse;
import com.smart.sotral.Shared.user.application.dtos.responses.UserResponse;
import com.smart.sotral.Shared.user.application.mappers.UserMapper;
import com.smart.sotral.Shared.user.domain.models.User;
import com.smart.sotral.Shared.user.domain.services.UserService;
import com.smart.sotral.Shared.user.infrastructure.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;

    public UserResponse createUser(UserRequest request){
        return createUsager(request);
    }

    @Override
    public UserResponse createAdmin(UserRequest request) {
        User admin = this.userMapper.toAdmin(request);
        User savedAdmin = this.userRepository.save(admin);
        return this.userMapper.toResponse(savedAdmin);
    }

    @Override
    public UserResponse createUsager(UserRequest request) {
        User usager = this.userMapper.toUsager(request);
        User savedUsager = this.userRepository.save(usager);
        return this.userMapper.toResponse(savedUsager);

    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateJwtToken(authentication);

            User userDetails = (User) authentication.getPrincipal();

            List<String> rolesList = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return new LoginResponse(
                    userDetails.getTrackingId(),
                    token,
                    "Bearer",
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    null, // phone removed
                    userDetails.getEmail(),
                    userDetails.getRole().name(),
                    rolesList,
                    null, // country removed
                    userDetails.isActive()
            );

        } catch (BadCredentialsException ex) {
            throw new IllegalArgumentException("Les paramètres de connexion sont incorrects");
        }
    }


    @Override
    public UserResponse getUserByTrackingId(UUID trackingId) {
        User user =
                userRepository
                        .findByTrackingId(trackingId)
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUserEtat(UUID trackingId, boolean etat) {
        User user =
                userRepository
                        .findByTrackingId(trackingId)
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        user.setActive(etat);
        user = userRepository.save(user);

        if (etat) {
/*
            EmailRequest emailRequest = buildRoleBasedEmailRequest(user, null);

            String template =
                    switch (user.getRole()) {
                        case PATIENT -> SotralEmailConstants.TEMPLATE_USER_PATIENT_ACTIVATION;
                        case DOCTOR -> SotralEmailConstants.TEMPLATE_USER_DOCTOR_ACTIVATION;
                        case PHARMACIST -> SotralEmailConstants.TEMPLATE_USER_PHARMACIST_ACTIVATION;
                        case TITAN_ADMIN -> SotralEmailConstants.TEMPLATE_USER_ADMIN_ACTIVATION;
                        default -> SotralEmailConstants.TEMPLATE_USER_ACTIVATION_DEFAULT;
                    };

            sendSubscriptionEmail(emailRequest, template);

 */
        }

        return userMapper.toResponse(user);
    }

    @Override
    public org.springframework.data.domain.Page<UserResponse> getAllUsers(int page, int size) {
        org.springframework.data.domain.Pageable pageable = 
            org.springframework.data.domain.PageRequest.of(page, size);
        
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public void deleteUser(UUID trackingId) {
        User user =
                userRepository
                        .findByTrackingId(trackingId)
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        userRepository.delete(user);
    }

    private EmailRequest buildAdminEmailRequest(User user) {
        return EmailRequest.builder()
                .mailFrom(MailConstants.EMAIL_FROM)
                .mailTo(user.getEmail())
                .mailSubject(" " + user.getFirstName() )
                .lastName(user.getFirstName())
                .username(user.getEmail())
                .password(user.getPassword())
                .lien(MailConstants.WEBSITE_URL)
                .contact(MailConstants.COMPANY_SUPPORT)
                .build();
    }
}
