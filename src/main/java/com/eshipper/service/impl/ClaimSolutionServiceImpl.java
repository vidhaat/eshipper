package com.eshipper.service.impl;

import com.eshipper.service.ClaimSolutionService;
import com.eshipper.domain.ClaimSolution;
import com.eshipper.repository.ClaimSolutionRepository;
import com.eshipper.service.dto.ClaimSolutionDTO;
import com.eshipper.service.mapper.ClaimSolutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimSolution}.
 */
@Service
@Transactional
public class ClaimSolutionServiceImpl implements ClaimSolutionService {

    private final Logger log = LoggerFactory.getLogger(ClaimSolutionServiceImpl.class);

    private final ClaimSolutionRepository claimSolutionRepository;

    private final ClaimSolutionMapper claimSolutionMapper;

    public ClaimSolutionServiceImpl(ClaimSolutionRepository claimSolutionRepository, ClaimSolutionMapper claimSolutionMapper) {
        this.claimSolutionRepository = claimSolutionRepository;
        this.claimSolutionMapper = claimSolutionMapper;
    }

    /**
     * Save a claimSolution.
     *
     * @param claimSolutionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimSolutionDTO save(ClaimSolutionDTO claimSolutionDTO) {
        log.debug("Request to save ClaimSolution : {}", claimSolutionDTO);
        ClaimSolution claimSolution = claimSolutionMapper.toEntity(claimSolutionDTO);
        claimSolution = claimSolutionRepository.save(claimSolution);
        return claimSolutionMapper.toDto(claimSolution);
    }

    /**
     * Get all the claimSolutions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimSolutionDTO> findAll() {
        log.debug("Request to get all ClaimSolutions");
        return claimSolutionRepository.findAll().stream()
            .map(claimSolutionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimSolution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimSolutionDTO> findOne(Long id) {
        log.debug("Request to get ClaimSolution : {}", id);
        return claimSolutionRepository.findById(id)
            .map(claimSolutionMapper::toDto);
    }

    /**
     * Delete the claimSolution by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimSolution : {}", id);
        claimSolutionRepository.deleteById(id);
    }
}
