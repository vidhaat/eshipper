package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimMissingDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimMissingDocument} and its DTO {@link ClaimMissingDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimDocumentTypeMapper.class, ShippingClaimMapper.class})
public interface ClaimMissingDocumentMapper extends EntityMapper<ClaimMissingDocumentDTO, ClaimMissingDocument> {

    @Mapping(source = "claimDocumentType.id", target = "claimDocumentTypeId")
    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ClaimMissingDocumentDTO toDto(ClaimMissingDocument claimMissingDocument);

    @Mapping(source = "claimDocumentTypeId", target = "claimDocumentType")
    @Mapping(source = "shippingClaimId", target = "shippingClaim")
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
