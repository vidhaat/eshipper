package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimCarrierRefundStatus} and its DTO {@link ClaimCarrierRefundStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimCarrierRefundStatusMapper extends EntityMapper<ClaimCarrierRefundStatusDTO, ClaimCarrierRefundStatus> {



    default ClaimCarrierRefundStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimCarrierRefundStatus claimCarrierRefundStatus = new ClaimCarrierRefundStatus();
        claimCarrierRefundStatus.setId(id);
        return claimCarrierRefundStatus;
    }
}
