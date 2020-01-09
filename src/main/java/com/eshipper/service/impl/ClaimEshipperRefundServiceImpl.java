package com.eshipper.service.impl;

import com.eshipper.service.ClaimEshipperRefundService;
import com.eshipper.domain.ClaimEshipperRefund;
import com.eshipper.repository.ClaimEshipperRefundRepository;
import com.eshipper.service.dto.ClaimEshipperRefundDTO;
import com.eshipper.service.mapper.ClaimEshipperRefundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimEshipperRefund}.
 */
@Service
@Transactional
public class ClaimEshipperRefundServiceImpl implements ClaimEshipperRefundService {

    private final Logger log = LoggerFactory.getLogger(ClaimEshipperRefundServiceImpl.class);

    private final ClaimEshipperRefundRepository claimEshipperRefundRepository;

    private final ClaimEshipperRefundMapper claimEshipperRefundMapper;

    public ClaimEshipperRefundServiceImpl(ClaimEshipperRefundRepository claimEshipperRefundRepository, ClaimEshipperRefundMapper claimEshipperRefundMapper) {
        this.claimEshipperRefundRepository = claimEshipperRefundRepository;
        this.claimEshipperRefundMapper = claimEshipperRefundMapper;
    }

    /**
     * Save a claimEshipperRefund.
     *
     * @param claimEshipperRefundDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimEshipperRefundDTO save(ClaimEshipperRefundDTO claimEshipperRefundDTO) {
        log.debug("Request to save ClaimEshipperRefund : {}", claimEshipperRefundDTO);
        ClaimEshipperRefund claimEshipperRefund = claimEshipperRefundMapper.toEntity(claimEshipperRefundDTO);
        claimEshipperRefund = claimEshipperRefundRepository.save(claimEshipperRefund);
        return claimEshipperRefundMapper.toDto(claimEshipperRefund);
    }

    /**
     * Get all the claimEshipperRefunds.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimEshipperRefundDTO> findAll() {
        log.debug("Request to get all ClaimEshipperRefunds");
        return claimEshipperRefundRepository.findAll().stream()
            .map(claimEshipperRefundMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimEshipperRefund by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimEshipperRefundDTO> findOne(Long id) {
        log.debug("Request to get ClaimEshipperRefund : {}", id);
        return claimEshipperRefundRepository.findById(id)
            .map(claimEshipperRefundMapper::toDto);
    }

    /**
     * Delete the claimEshipperRefund by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimEshipperRefund : {}", id);
        claimEshipperRefundRepository.deleteById(id);
    }
}
