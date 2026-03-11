package com.smart.sotral.Shared.mailling.service;

import com.smart.sotral.Shared.mailling.dto.request.EmailRequest;
import com.smart.sotral.Shared.mailling.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse send(EmailRequest request, String template);
}
