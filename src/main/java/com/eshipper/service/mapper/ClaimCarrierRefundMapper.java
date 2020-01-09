package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCarrierRefundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimCarrierRefund} and its DTO {@link ClaimCarrierRefundDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimCarrierRefundMapper extends EntityMapper<ClaimCarrierRefundDTO, ClaimCarrierRefund> {


    @Mapping(target = "currencies", ignore = true)
    @Mapping(target = "removeCurrency", ignore = true)
    @Mapping(target = "refundStatuses", ignore = true)
    @Mapping(target = "removeRefundStatus", ignore = true)
    ClaimCarrierRefund toEntity(ClaimCarrierRefundDTO claimCarrierRefundDTO);

    default ClaimCarrierRefund fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimCarrierRefund claimCarrierRefund = new ClaimCarrierRefund();
        claimCarrierRefund.setId(id);
        return claimCarrierRefund;
    }
}
