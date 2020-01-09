package com.eshipper.service.impl;

import com.eshipper.service.ClaimStatusService;
import com.eshipper.domain.ClaimStatus;
import com.eshipper.repository.ClaimStatusRepository;
import com.eshipper.service.dto.ClaimStatusDTO;
import com.eshipper.service.mapper.ClaimStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimStatus}.
 */
@Service
@Transactional
public class ClaimStatusServiceImpl implements ClaimStatusService {

    private final Logger log = LoggerFactory.getLogger(ClaimStatusServiceImpl.class);

    private final ClaimStatusRepository claimStatusRepository;

    private final ClaimStatusMapper claimStatusMapper;

    public ClaimStatusServiceImpl(ClaimStatusRepository claimStatusRepository, ClaimStatusMapper claimStatusMapper) {
        this.claimStatusRepository = claimStatusRepository;
        this.claimStatusMapper = claimStatusMapper;
    }

    /**
     * Save a claimStatus.
     *
     * @param claimStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimStatusDTO save(ClaimStatusDTO claimStatusDTO) {
        log.debug("Request to save ClaimStatus : {}", claimStatusDTO);
        ClaimStatus claimStatus = claimStatusMapper.toEntity(claimStatusDTO);
        claimStatus = claimStatusRepository.save(claimStatus);
        return claimStatusMapper.toDto(claimStatus);
    }

    /**
     * Get all the claimStatuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimStatusDTO> findAll() {
        log.debug("Request to get all ClaimStatuses");
        return claimStatusRepository.findAll().stream()
            .map(claimStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimStatus : {}", id);
        return claimStatusRepository.findById(id)
            .map(claimStatusMapper::toDto);
    }

    /**
     * Delete the claimStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimStatus : {}", id);
        claimStatusRepository.deleteById(id);
    }
}
