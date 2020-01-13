package com.eshipper.service.impl;

import com.eshipper.service.ElasticShippingClaimService;
import com.eshipper.domain.ElasticShippingClaim;
import com.eshipper.repository.ElasticShippingClaimRepository;
import com.eshipper.service.dto.ElasticShippingClaimDTO;
import com.eshipper.service.mapper.ElasticShippingClaimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ElasticShippingClaim}.
 */
@Service
@Transactional
public class ElasticShippingClaimServiceImpl implements ElasticShippingClaimService {

    private final Logger log = LoggerFactory.getLogger(ElasticShippingClaimServiceImpl.class);

    private final ElasticShippingClaimRepository elasticShippingClaimRepository;

    private final ElasticShippingClaimMapper elasticShippingClaimMapper;

    public ElasticShippingClaimServiceImpl(ElasticShippingClaimRepository elasticShippingClaimRepository, ElasticShippingClaimMapper elasticShippingClaimMapper) {
        this.elasticShippingClaimRepository = elasticShippingClaimRepository;
        this.elasticShippingClaimMapper = elasticShippingClaimMapper;
    }

    /**
     * Save a elasticShippingClaim.
     *
     * @param elasticShippingClaimDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticShippingClaimDTO save(ElasticShippingClaimDTO elasticShippingClaimDTO) {
        log.debug("Request to save ElasticShippingClaim : {}", elasticShippingClaimDTO);
        ElasticShippingClaim elasticShippingClaim = elasticShippingClaimMapper.toEntity(elasticShippingClaimDTO);
        elasticShippingClaim = elasticShippingClaimRepository.save(elasticShippingClaim);
        return elasticShippingClaimMapper.toDto(elasticShippingClaim);
    }

    /**
     * Get all the elasticShippingClaims.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ElasticShippingClaimDTO> findAll() {
        log.debug("Request to get all ElasticShippingClaims");
        return elasticShippingClaimRepository.findAll().stream()
            .map(elasticShippingClaimMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one elasticShippingClaim by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticShippingClaimDTO> findOne(Long id) {
        log.debug("Request to get ElasticShippingClaim : {}", id);
        return elasticShippingClaimRepository.findById(id)
            .map(elasticShippingClaimMapper::toDto);
    }

    /**
     * Delete the elasticShippingClaim by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticShippingClaim : {}", id);
        elasticShippingClaimRepository.deleteById(id);
    }
}
