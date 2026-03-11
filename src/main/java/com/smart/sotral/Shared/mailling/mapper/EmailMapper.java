package com.smart.sotral.Shared.mailling.mapper;

import com.smart.sotral.Shared.mailling.dto.request.EmailRequest;
import com.smart.sotral.Shared.mailling.dto.response.EmailResponse;
import com.smart.sotral.Shared.mailling.entity.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailMapper {
    public EmailResponse toDto(Email email) {
        EmailResponse response = new EmailResponse();
        BeanUtils.copyProperties(email, response);
        return response;
    }

    public Email toEntity(EmailRequest request) {
        Email email = new Email();
        BeanUtils.copyProperties(request, email);
        return email;
    }

    public Email toEntity(EmailRequest request, Email email) {
        BeanUtils.copyProperties(request, email);
        return email;
    }
}
