package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClaimSolutionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimSolution} and its DTO {@link ClaimSolutionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimSolutionMapper extends EntityMapper<ClaimSolutionDTO, ClaimSolution> {


    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
    ClaimSolution toEntity(ClaimSolutionDTO claimSolutionDTO);

    default ClaimSolution fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimSolution claimSolution = new ClaimSolution();
        claimSolution.setId(id);
        return claimSolution;
    }
}
