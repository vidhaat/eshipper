package com.eshipper.service;

import com.eshipper.service.dto.ClaimStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimStatus}.
 */
public interface ClaimStatusService {

    /**
     * Save a claimStatus.
     *
     * @param claimStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimStatusDTO save(ClaimStatusDTO claimStatusDTO);

    /**
     * Get all the claimStatuses.
     *
     * @return the list of entities.
     */
    List<ClaimStatusDTO> findAll();


    /**
     * Get the "id" claimStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
