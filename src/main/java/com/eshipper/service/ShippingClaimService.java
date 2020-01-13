package com.eshipper.service;

import com.eshipper.service.dto.ShippingClaimDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ShippingClaim}.
 */
public interface ShippingClaimService {

    /**
     * Save a shippingClaim.
     *
     * @param shippingClaimDTO the entity to save.
     * @return the persisted entity.
     */
    ShippingClaimDTO save(ShippingClaimDTO shippingClaimDTO);

    /**
     * Get all the shippingClaims.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShippingClaimDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shippingClaim.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShippingClaimDTO> findOne(Long id);

    /**
     * Delete the "id" shippingClaim.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
