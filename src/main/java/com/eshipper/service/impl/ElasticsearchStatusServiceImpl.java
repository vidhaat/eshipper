package com.eshipper.service.impl;

import com.eshipper.service.ElasticsearchStatusService;
import com.eshipper.domain.ElasticsearchStatus;
import com.eshipper.repository.ElasticsearchStatusRepository;
import com.eshipper.service.dto.ElasticsearchStatusDTO;
import com.eshipper.service.mapper.ElasticsearchStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ElasticsearchStatus}.
 */
@Service
@Transactional
public class ElasticsearchStatusServiceImpl implements ElasticsearchStatusService {

    private final Logger log = LoggerFactory.getLogger(ElasticsearchStatusServiceImpl.class);

    private final ElasticsearchStatusRepository elasticsearchStatusRepository;

    private final ElasticsearchStatusMapper elasticsearchStatusMapper;

    public ElasticsearchStatusServiceImpl(ElasticsearchStatusRepository elasticsearchStatusRepository, ElasticsearchStatusMapper elasticsearchStatusMapper) {
        this.elasticsearchStatusRepository = elasticsearchStatusRepository;
        this.elasticsearchStatusMapper = elasticsearchStatusMapper;
    }

    /**
     * Save a elasticsearchStatus.
     *
     * @param elasticsearchStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticsearchStatusDTO save(ElasticsearchStatusDTO elasticsearchStatusDTO) {
        log.debug("Request to save ElasticsearchStatus : {}", elasticsearchStatusDTO);
        ElasticsearchStatus elasticsearchStatus = elasticsearchStatusMapper.toEntity(elasticsearchStatusDTO);
        elasticsearchStatus = elasticsearchStatusRepository.save(elasticsearchStatus);
        return elasticsearchStatusMapper.toDto(elasticsearchStatus);
    }

    /**
     * Get all the elasticsearchStatuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ElasticsearchStatusDTO> findAll() {
        log.debug("Request to get all ElasticsearchStatuses");
        return elasticsearchStatusRepository.findAll().stream()
            .map(elasticsearchStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one elasticsearchStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticsearchStatusDTO> findOne(Long id) {
        log.debug("Request to get ElasticsearchStatus : {}", id);
        return elasticsearchStatusRepository.findById(id)
            .map(elasticsearchStatusMapper::toDto);
    }

    /**
     * Delete the elasticsearchStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticsearchStatus : {}", id);
        elasticsearchStatusRepository.deleteById(id);
    }
}
