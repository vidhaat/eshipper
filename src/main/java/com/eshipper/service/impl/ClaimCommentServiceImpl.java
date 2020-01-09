package com.eshipper.service.impl;

import com.eshipper.service.ClaimCommentService;
import com.eshipper.domain.ClaimComment;
import com.eshipper.repository.ClaimCommentRepository;
import com.eshipper.service.dto.ClaimCommentDTO;
import com.eshipper.service.mapper.ClaimCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimComment}.
 */
@Service
@Transactional
public class ClaimCommentServiceImpl implements ClaimCommentService {

    private final Logger log = LoggerFactory.getLogger(ClaimCommentServiceImpl.class);

    private final ClaimCommentRepository claimCommentRepository;

    private final ClaimCommentMapper claimCommentMapper;

    public ClaimCommentServiceImpl(ClaimCommentRepository claimCommentRepository, ClaimCommentMapper claimCommentMapper) {
        this.claimCommentRepository = claimCommentRepository;
        this.claimCommentMapper = claimCommentMapper;
    }

    /**
     * Save a claimComment.
     *
     * @param claimCommentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimCommentDTO save(ClaimCommentDTO claimCommentDTO) {
        log.debug("Request to save ClaimComment : {}", claimCommentDTO);
        ClaimComment claimComment = claimCommentMapper.toEntity(claimCommentDTO);
        claimComment = claimCommentRepository.save(claimComment);
        return claimCommentMapper.toDto(claimComment);
    }

    /**
     * Get all the claimComments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimCommentDTO> findAll() {
        log.debug("Request to get all ClaimComments");
        return claimCommentRepository.findAll().stream()
            .map(claimCommentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimComment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimCommentDTO> findOne(Long id) {
        log.debug("Request to get ClaimComment : {}", id);
        return claimCommentRepository.findById(id)
            .map(claimCommentMapper::toDto);
    }

    /**
     * Delete the claimComment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimComment : {}", id);
        claimCommentRepository.deleteById(id);
    }
}
