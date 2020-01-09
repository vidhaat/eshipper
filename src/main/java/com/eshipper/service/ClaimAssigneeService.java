package com.eshipper.service;

import com.eshipper.service.dto.ClaimAssigneeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimAssignee}.
 */
public interface ClaimAssigneeService {

    /**
     * Save a claimAssignee.
     *
     * @param claimAssigneeDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimAssigneeDTO save(ClaimAssigneeDTO claimAssigneeDTO);

    /**
     * Get all the claimAssignees.
     *
     * @return the list of entities.
     */
    List<ClaimAssigneeDTO> findAll();


    /**
     * Get the "id" claimAssignee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimAssigneeDTO> findOne(Long id);

    /**
     * Delete the "id" claimAssignee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
