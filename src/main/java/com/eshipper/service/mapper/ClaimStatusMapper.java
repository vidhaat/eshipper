package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimStatus} and its DTO {@link ClaimStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class})
public interface ClaimStatusMapper extends EntityMapper<ClaimStatusDTO, ClaimStatus> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ClaimStatusDTO toDto(ClaimStatus claimStatus);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
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
