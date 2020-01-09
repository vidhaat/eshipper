package com.eshipper.service;

import com.eshipper.service.dto.ContactPreferenceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ContactPreference}.
 */
public interface ContactPreferenceService {

    /**
     * Save a contactPreference.
     *
     * @param contactPreferenceDTO the entity to save.
     * @return the persisted entity.
     */
    ContactPreferenceDTO save(ContactPreferenceDTO contactPreferenceDTO);

    /**
     * Get all the contactPreferences.
     *
     * @return the list of entities.
     */
    List<ContactPreferenceDTO> findAll();


    /**
     * Get the "id" contactPreference.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactPreferenceDTO> findOne(Long id);

    /**
     * Delete the "id" contactPreference.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
