package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimAssigneeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimAssignee} and its DTO {@link ClaimAssigneeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class})
public interface ClaimAssigneeMapper extends EntityMapper<ClaimAssigneeDTO, ClaimAssignee> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ClaimAssigneeDTO toDto(ClaimAssignee claimAssignee);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    ClaimAssignee toEntity(ClaimAssigneeDTO claimAssigneeDTO);

    default ClaimAssignee fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimAssignee claimAssignee = new ClaimAssignee();
        claimAssignee.setId(id);
        return claimAssignee;
    }
}
