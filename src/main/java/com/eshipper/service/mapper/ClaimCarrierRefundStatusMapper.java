package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimCarrierRefundStatus} and its DTO {@link ClaimCarrierRefundStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimCarrierRefundMapper.class})
public interface ClaimCarrierRefundStatusMapper extends EntityMapper<ClaimCarrierRefundStatusDTO, ClaimCarrierRefundStatus> {

    @Mapping(source = "claimCarrierRefund.id", target = "claimCarrierRefundId")
    ClaimCarrierRefundStatusDTO toDto(ClaimCarrierRefundStatus claimCarrierRefundStatus);

    @Mapping(source = "claimCarrierRefundId", target = "claimCarrierRefund")
    ClaimCarrierRefundStatus toEntity(ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO);

    default ClaimCarrierRefundStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimCarrierRefundStatus claimCarrierRefundStatus = new ClaimCarrierRefundStatus();
        claimCarrierRefundStatus.setId(id);
        return claimCarrierRefundStatus;
    }
}
