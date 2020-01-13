package com.eshipper.service;

import com.eshipper.service.dto.ClaimSolutionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimSolution}.
 */
public interface ClaimSolutionService {

    /**
     * Save a claimSolution.
     *
     * @param claimSolutionDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimSolutionDTO save(ClaimSolutionDTO claimSolutionDTO);

    /**
     * Get all the claimSolutions.
     *
     * @return the list of entities.
     */
    List<ClaimSolutionDTO> findAll();


    /**
     * Get the "id" claimSolution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimSolutionDTO> findOne(Long id);

    /**
     * Delete the "id" claimSolution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
