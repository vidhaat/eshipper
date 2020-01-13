package com.eshipper.service;

import com.eshipper.service.dto.ClaimCommentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimComment}.
 */
public interface ClaimCommentService {

    /**
     * Save a claimComment.
     *
     * @param claimCommentDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimCommentDTO save(ClaimCommentDTO claimCommentDTO);

    /**
     * Get all the claimComments.
     *
     * @return the list of entities.
     */
    List<ClaimCommentDTO> findAll();


    /**
     * Get the "id" claimComment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimCommentDTO> findOne(Long id);

    /**
     * Delete the "id" claimComment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
