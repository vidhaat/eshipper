package com.eshipper.service;

import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimCarrierRefundStatus}.
 */
public interface ClaimCarrierRefundStatusService {

    /**
     * Save a claimCarrierRefundStatus.
     *
     * @param claimCarrierRefundStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimCarrierRefundStatusDTO save(ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO);

    /**
     * Get all the claimCarrierRefundStatuses.
     *
     * @return the list of entities.
     */
    List<ClaimCarrierRefundStatusDTO> findAll();


    /**
     * Get the "id" claimCarrierRefundStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimCarrierRefundStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimCarrierRefundStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
