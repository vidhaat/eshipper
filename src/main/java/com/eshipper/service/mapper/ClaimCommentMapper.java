package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimComment} and its DTO {@link ClaimCommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {User1Mapper.class})
public interface ClaimCommentMapper extends EntityMapper<ClaimCommentDTO, ClaimComment> {

    @Mapping(source = "user1.id", target = "user1Id")
    ClaimCommentDTO toDto(ClaimComment claimComment);

    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
    @Mapping(source = "user1Id", target = "user1")
    ClaimComment toEntity(ClaimCommentDTO claimCommentDTO);

    default ClaimComment fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimComment claimComment = new ClaimComment();
        claimComment.setId(id);
        return claimComment;
    }
}
