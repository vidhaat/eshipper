package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimCommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimComment} and its DTO {@link ClaimCommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClaimCommentMapper extends EntityMapper<ClaimCommentDTO, ClaimComment> {

    @Mapping(source = "user.id", target = "userId")
    ClaimCommentDTO toDto(ClaimComment claimComment);

    @Mapping(source = "userId", target = "user")
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
