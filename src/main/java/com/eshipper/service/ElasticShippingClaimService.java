package com.eshipper.service;

import com.eshipper.service.dto.ElasticShippingClaimDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticShippingClaim}.
 */
public interface ElasticShippingClaimService {

    /**
     * Save a elasticShippingClaim.
     *
     * @param elasticShippingClaimDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticShippingClaimDTO save(ElasticShippingClaimDTO elasticShippingClaimDTO);

    /**
     * Get all the elasticShippingClaims.
     *
     * @return the list of entities.
     */
    List<ElasticShippingClaimDTO> findAll();


    /**
     * Get the "id" elasticShippingClaim.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticShippingClaimDTO> findOne(Long id);

    /**
     * Delete the "id" elasticShippingClaim.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
