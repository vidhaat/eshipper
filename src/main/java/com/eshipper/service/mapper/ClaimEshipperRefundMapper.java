package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimEshipperRefundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimEshipperRefund} and its DTO {@link ClaimEshipperRefundDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimEshipperRefundMapper extends EntityMapper<ClaimEshipperRefundDTO, ClaimEshipperRefund> {


    @Mapping(target = "currencies", ignore = true)
    @Mapping(target = "removeCurrency", ignore = true)
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
