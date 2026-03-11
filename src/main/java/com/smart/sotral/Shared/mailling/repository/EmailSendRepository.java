package com.smart.sotral.Shared.mailling.repository;

import com.smart.sotral.Shared.mailling.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendRepository extends JpaRepository<Email, Long> {}
