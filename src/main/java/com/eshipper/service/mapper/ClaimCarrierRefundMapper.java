package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCarrierRefundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimCarrierRefund} and its DTO {@link ClaimCarrierRefundDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class, ClaimCarrierRefundStatusMapper.class})
public interface ClaimCarrierRefundMapper extends EntityMapper<ClaimCarrierRefundDTO, ClaimCarrierRefund> {

    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "refundStatus.id", target = "refundStatusId")
    ClaimCarrierRefundDTO toDto(ClaimCarrierRefund claimCarrierRefund);

    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "refundStatusId", target = "refundStatus")
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
