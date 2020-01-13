package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimAttachment} and its DTO {@link ClaimAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class})
public interface ClaimAttachmentMapper extends EntityMapper<ClaimAttachmentDTO, ClaimAttachment> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ClaimAttachmentDTO toDto(ClaimAttachment claimAttachment);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    ClaimAttachment toEntity(ClaimAttachmentDTO claimAttachmentDTO);

    default ClaimAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimAttachment claimAttachment = new ClaimAttachment();
        claimAttachment.setId(id);
        return claimAttachment;
    }
}
