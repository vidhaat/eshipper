package com.eshipper.service.impl;

import com.eshipper.service.ClaimAssigneeService;
import com.eshipper.domain.ClaimAssignee;
import com.eshipper.repository.ClaimAssigneeRepository;
import com.eshipper.service.dto.ClaimAssigneeDTO;
import com.eshipper.service.mapper.ClaimAssigneeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimAssignee}.
 */
@Service
@Transactional
public class ClaimAssigneeServiceImpl implements ClaimAssigneeService {

    private final Logger log = LoggerFactory.getLogger(ClaimAssigneeServiceImpl.class);

    private final ClaimAssigneeRepository claimAssigneeRepository;

    private final ClaimAssigneeMapper claimAssigneeMapper;

    public ClaimAssigneeServiceImpl(ClaimAssigneeRepository claimAssigneeRepository, ClaimAssigneeMapper claimAssigneeMapper) {
        this.claimAssigneeRepository = claimAssigneeRepository;
        this.claimAssigneeMapper = claimAssigneeMapper;
    }

    /**
     * Save a claimAssignee.
     *
     * @param claimAssigneeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimAssigneeDTO save(ClaimAssigneeDTO claimAssigneeDTO) {
        log.debug("Request to save ClaimAssignee : {}", claimAssigneeDTO);
        ClaimAssignee claimAssignee = claimAssigneeMapper.toEntity(claimAssigneeDTO);
        claimAssignee = claimAssigneeRepository.save(claimAssignee);
        return claimAssigneeMapper.toDto(claimAssignee);
    }

    /**
     * Get all the claimAssignees.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimAssigneeDTO> findAll() {
        log.debug("Request to get all ClaimAssignees");
        return claimAssigneeRepository.findAll().stream()
            .map(claimAssigneeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimAssignee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimAssigneeDTO> findOne(Long id) {
        log.debug("Request to get ClaimAssignee : {}", id);
        return claimAssigneeRepository.findById(id)
            .map(claimAssigneeMapper::toDto);
    }

    /**
     * Delete the claimAssignee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimAssignee : {}", id);
        claimAssigneeRepository.deleteById(id);
    }
}
