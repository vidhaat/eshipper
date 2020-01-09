package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimDocumentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimDocumentType} and its DTO {@link ClaimDocumentTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimMissingDocumentMapper.class})
public interface ClaimDocumentTypeMapper extends EntityMapper<ClaimDocumentTypeDTO, ClaimDocumentType> {

    @Mapping(source = "claimMissingDocument.id", target = "claimMissingDocumentId")
    ClaimDocumentTypeDTO toDto(ClaimDocumentType claimDocumentType);

    @Mapping(source = "claimMissingDocumentId", target = "claimMissingDocument")
    ClaimDocumentType toEntity(ClaimDocumentTypeDTO claimDocumentTypeDTO);

    default ClaimDocumentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimDocumentType claimDocumentType = new ClaimDocumentType();
        claimDocumentType.setId(id);
        return claimDocumentType;
    }
}
