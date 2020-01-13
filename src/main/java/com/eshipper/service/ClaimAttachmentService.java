package com.eshipper.service;

import com.eshipper.service.dto.ClaimAttachmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimAttachment}.
 */
public interface ClaimAttachmentService {

    /**
     * Save a claimAttachment.
     *
     * @param claimAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimAttachmentDTO save(ClaimAttachmentDTO claimAttachmentDTO);

    /**
     * Get all the claimAttachments.
     *
     * @return the list of entities.
     */
    List<ClaimAttachmentDTO> findAll();


    /**
     * Get the "id" claimAttachment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimAttachmentDTO> findOne(Long id);

    /**
     * Delete the "id" claimAttachment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
