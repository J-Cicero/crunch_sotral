package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.domain.models.Bus;

@Component
public class BusMapper {
    
    public static Bus toEntity(BusRequest request) {
        Bus b = new Bus();
        b.setCode(request.getCode());
        return b;
    }

    public static BusResponse toResponse(Bus entity) {
        return BusResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .code(entity.getCode())
                .build();
    }
}
