package com.eshipper.service.impl;

import com.eshipper.service.ClaimCarrierRefundService;
import com.eshipper.domain.ClaimCarrierRefund;
import com.eshipper.repository.ClaimCarrierRefundRepository;
import com.eshipper.service.dto.ClaimCarrierRefundDTO;
import com.eshipper.service.mapper.ClaimCarrierRefundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimCarrierRefund}.
 */
@Service
@Transactional
public class ClaimCarrierRefundServiceImpl implements ClaimCarrierRefundService {

    private final Logger log = LoggerFactory.getLogger(ClaimCarrierRefundServiceImpl.class);

    private final ClaimCarrierRefundRepository claimCarrierRefundRepository;

    private final ClaimCarrierRefundMapper claimCarrierRefundMapper;

    public ClaimCarrierRefundServiceImpl(ClaimCarrierRefundRepository claimCarrierRefundRepository, ClaimCarrierRefundMapper claimCarrierRefundMapper) {
        this.claimCarrierRefundRepository = claimCarrierRefundRepository;
        this.claimCarrierRefundMapper = claimCarrierRefundMapper;
    }

    /**
     * Save a claimCarrierRefund.
     *
     * @param claimCarrierRefundDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimCarrierRefundDTO save(ClaimCarrierRefundDTO claimCarrierRefundDTO) {
        log.debug("Request to save ClaimCarrierRefund : {}", claimCarrierRefundDTO);
        ClaimCarrierRefund claimCarrierRefund = claimCarrierRefundMapper.toEntity(claimCarrierRefundDTO);
        claimCarrierRefund = claimCarrierRefundRepository.save(claimCarrierRefund);
        return claimCarrierRefundMapper.toDto(claimCarrierRefund);
    }

    /**
     * Get all the claimCarrierRefunds.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimCarrierRefundDTO> findAll() {
        log.debug("Request to get all ClaimCarrierRefunds");
        return claimCarrierRefundRepository.findAll().stream()
            .map(claimCarrierRefundMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimCarrierRefund by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimCarrierRefundDTO> findOne(Long id) {
        log.debug("Request to get ClaimCarrierRefund : {}", id);
        return claimCarrierRefundRepository.findById(id)
            .map(claimCarrierRefundMapper::toDto);
    }

    /**
     * Delete the claimCarrierRefund by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimCarrierRefund : {}", id);
        claimCarrierRefundRepository.deleteById(id);
    }
}
