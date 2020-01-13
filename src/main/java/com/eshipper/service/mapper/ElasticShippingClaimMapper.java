package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticShippingClaimDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticShippingClaim} and its DTO {@link ElasticShippingClaimDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class, ElasticsearchStatusMapper.class})
public interface ElasticShippingClaimMapper extends EntityMapper<ElasticShippingClaimDTO, ElasticShippingClaim> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    @Mapping(source = "elasticStatus.id", target = "elasticStatusId")
    ElasticShippingClaimDTO toDto(ElasticShippingClaim elasticShippingClaim);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    @Mapping(source = "elasticStatusId", target = "elasticStatus")
    ElasticShippingClaim toEntity(ElasticShippingClaimDTO elasticShippingClaimDTO);

    default ElasticShippingClaim fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticShippingClaim elasticShippingClaim = new ElasticShippingClaim();
        elasticShippingClaim.setId(id);
        return elasticShippingClaim;
    }
}
