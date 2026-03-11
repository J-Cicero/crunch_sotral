package com.smart.sotral.Shared.mailling.repository;

import com.smart.sotral.Shared.mailling.entity.EmailConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailConfiguration, Long> {
    EmailConfiguration findFirstByOrderByIdAsc();
}
