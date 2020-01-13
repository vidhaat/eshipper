package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimEshipperRefundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimEshipperRefund} and its DTO {@link ClaimEshipperRefundDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public interface ClaimEshipperRefundMapper extends EntityMapper<ClaimEshipperRefundDTO, ClaimEshipperRefund> {

    @Mapping(source = "currency.id", target = "currencyId")
    ClaimEshipperRefundDTO toDto(ClaimEshipperRefund claimEshipperRefund);

    @Mapping(source = "currencyId", target = "currency")
    ClaimEshipperRefund toEntity(ClaimEshipperRefundDTO claimEshipperRefundDTO);

    default ClaimEshipperRefund fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimEshipperRefund claimEshipperRefund = new ClaimEshipperRefund();
        claimEshipperRefund.setId(id);
        return claimEshipperRefund;
    }
}
