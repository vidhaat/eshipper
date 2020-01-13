package com.eshipper.service.impl;

import com.eshipper.service.ClaimCarrierRefundStatusService;
import com.eshipper.domain.ClaimCarrierRefundStatus;
import com.eshipper.repository.ClaimCarrierRefundStatusRepository;
import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;
import com.eshipper.service.mapper.ClaimCarrierRefundStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimCarrierRefundStatus}.
 */
@Service
@Transactional
public class ClaimCarrierRefundStatusServiceImpl implements ClaimCarrierRefundStatusService {

    private final Logger log = LoggerFactory.getLogger(ClaimCarrierRefundStatusServiceImpl.class);

    private final ClaimCarrierRefundStatusRepository claimCarrierRefundStatusRepository;

    private final ClaimCarrierRefundStatusMapper claimCarrierRefundStatusMapper;

    public ClaimCarrierRefundStatusServiceImpl(ClaimCarrierRefundStatusRepository claimCarrierRefundStatusRepository, ClaimCarrierRefundStatusMapper claimCarrierRefundStatusMapper) {
        this.claimCarrierRefundStatusRepository = claimCarrierRefundStatusRepository;
        this.claimCarrierRefundStatusMapper = claimCarrierRefundStatusMapper;
    }

    /**
     * Save a claimCarrierRefundStatus.
     *
     * @param claimCarrierRefundStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimCarrierRefundStatusDTO save(ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO) {
        log.debug("Request to save ClaimCarrierRefundStatus : {}", claimCarrierRefundStatusDTO);
        ClaimCarrierRefundStatus claimCarrierRefundStatus = claimCarrierRefundStatusMapper.toEntity(claimCarrierRefundStatusDTO);
        claimCarrierRefundStatus = claimCarrierRefundStatusRepository.save(claimCarrierRefundStatus);
        return claimCarrierRefundStatusMapper.toDto(claimCarrierRefundStatus);
    }

    /**
     * Get all the claimCarrierRefundStatuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimCarrierRefundStatusDTO> findAll() {
        log.debug("Request to get all ClaimCarrierRefundStatuses");
        return claimCarrierRefundStatusRepository.findAll().stream()
            .map(claimCarrierRefundStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimCarrierRefundStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimCarrierRefundStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimCarrierRefundStatus : {}", id);
        return claimCarrierRefundStatusRepository.findById(id)
            .map(claimCarrierRefundStatusMapper::toDto);
    }

    /**
     * Delete the claimCarrierRefundStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimCarrierRefundStatus : {}", id);
        claimCarrierRefundStatusRepository.deleteById(id);
    }
}
