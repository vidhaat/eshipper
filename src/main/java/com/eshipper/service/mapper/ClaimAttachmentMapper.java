package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimAttachment} and its DTO {@link ClaimAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimAttachmentMapper extends EntityMapper<ClaimAttachmentDTO, ClaimAttachment> {


    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
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
