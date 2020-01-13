package com.eshipper.service;

import com.eshipper.service.dto.ClaimMissingDocumentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimMissingDocument}.
 */
public interface ClaimMissingDocumentService {

    /**
     * Save a claimMissingDocument.
     *
     * @param claimMissingDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimMissingDocumentDTO save(ClaimMissingDocumentDTO claimMissingDocumentDTO);

    /**
     * Get all the claimMissingDocuments.
     *
     * @return the list of entities.
     */
    List<ClaimMissingDocumentDTO> findAll();


    /**
     * Get the "id" claimMissingDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimMissingDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" claimMissingDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
