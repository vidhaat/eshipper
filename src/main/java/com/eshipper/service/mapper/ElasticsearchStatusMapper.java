package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticsearchStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticsearchStatus} and its DTO {@link ElasticsearchStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ElasticsearchStatusMapper extends EntityMapper<ElasticsearchStatusDTO, ElasticsearchStatus> {


    @Mapping(target = "elasticShippingClaims", ignore = true)
    @Mapping(target = "removeElasticShippingClaim", ignore = true)
    ElasticsearchStatus toEntity(ElasticsearchStatusDTO elasticsearchStatusDTO);

    default ElasticsearchStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticsearchStatus elasticsearchStatus = new ElasticsearchStatus();
        elasticsearchStatus.setId(id);
        return elasticsearchStatus;
    }
}
