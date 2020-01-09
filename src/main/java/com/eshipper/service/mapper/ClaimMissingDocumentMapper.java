package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimMissingDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimMissingDocument} and its DTO {@link ClaimMissingDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimMissingDocumentMapper extends EntityMapper<ClaimMissingDocumentDTO, ClaimMissingDocument> {


    @Mapping(target = "claimDocumentTypes", ignore = true)
    @Mapping(target = "removeClaimDocumentType", ignore = true)
    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
    ClaimMissingDocument toEntity(ClaimMissingDocumentDTO claimMissingDocumentDTO);

    default ClaimMissingDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimMissingDocument claimMissingDocument = new ClaimMissingDocument();
        claimMissingDocument.setId(id);
        return claimMissingDocument;
    }
}
