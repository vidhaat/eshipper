package com.eshipper.service;

import com.eshipper.service.dto.ElasticsearchStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticsearchStatus}.
 */
public interface ElasticsearchStatusService {

    /**
     * Save a elasticsearchStatus.
     *
     * @param elasticsearchStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticsearchStatusDTO save(ElasticsearchStatusDTO elasticsearchStatusDTO);

    /**
     * Get all the elasticsearchStatuses.
     *
     * @return the list of entities.
     */
    List<ElasticsearchStatusDTO> findAll();


    /**
     * Get the "id" elasticsearchStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticsearchStatusDTO> findOne(Long id);

    /**
     * Delete the "id" elasticsearchStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
