package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimStatus} and its DTO {@link ClaimStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimStatusMapper extends EntityMapper<ClaimStatusDTO, ClaimStatus> {


    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
    ClaimStatus toEntity(ClaimStatusDTO claimStatusDTO);

    default ClaimStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimStatus claimStatus = new ClaimStatus();
        claimStatus.setId(id);
        return claimStatus;
    }
}
