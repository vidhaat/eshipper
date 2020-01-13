package com.eshipper.service;

import com.eshipper.service.dto.ClaimDocumentTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimDocumentType}.
 */
public interface ClaimDocumentTypeService {

    /**
     * Save a claimDocumentType.
     *
     * @param claimDocumentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimDocumentTypeDTO save(ClaimDocumentTypeDTO claimDocumentTypeDTO);

    /**
     * Get all the claimDocumentTypes.
     *
     * @return the list of entities.
     */
    List<ClaimDocumentTypeDTO> findAll();


    /**
     * Get the "id" claimDocumentType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimDocumentTypeDTO> findOne(Long id);

    /**
     * Delete the "id" claimDocumentType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
