package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.Ligne;

public class BusMapper {
    public static Bus toEntity(BusRequest request, Ligne ligne) {
        Bus b = new Bus();
        b.setCode(request.getCode());
        b.setLigne(ligne);
        return b;
    }

    public static BusResponse toResponse(Bus entity) {
        return BusResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .ligneId(entity.getLigne() != null ? entity.getLigne().getId() : null)
                .build();
    }
}
