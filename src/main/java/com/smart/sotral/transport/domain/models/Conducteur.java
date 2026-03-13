package com.smart.sotral.transport.domain.models;

import java.time.LocalDate;

import com.smart.sotral.Shared.user.domain.enums.TypeRole;
import com.smart.sotral.Shared.user.domain.models.User;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CONDUCTEUR")
@Getter
@Setter
public class Conducteur extends User {

    private LocalDate dateEmbauche;

    @PrePersist
    public void ensureRole() {
        if (getRole() == null) {
            setRole(TypeRole.CONDUCTEUR);
        }
    }
}
