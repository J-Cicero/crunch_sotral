package com.smart.sotral.Shared.user.domain.models;

import com.smart.sotral.Shared.user.domain.enums.TypeRole;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
public class Admin extends User {

    @PrePersist
    public void ensureRole() {
        if (getRole() == null) {
            setRole(TypeRole.ADMIN);
        }
    }
}
