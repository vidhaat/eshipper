package com.eshipper.service.impl;

import com.eshipper.service.ClaimAttachmentService;
import com.eshipper.domain.ClaimAttachment;
import com.eshipper.repository.ClaimAttachmentRepository;
import com.eshipper.service.dto.ClaimAttachmentDTO;
import com.eshipper.service.mapper.ClaimAttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimAttachment}.
 */
@Service
@Transactional
public class ClaimAttachmentServiceImpl implements ClaimAttachmentService {

    private final Logger log = LoggerFactory.getLogger(ClaimAttachmentServiceImpl.class);

    private final ClaimAttachmentRepository claimAttachmentRepository;

    private final ClaimAttachmentMapper claimAttachmentMapper;

    public ClaimAttachmentServiceImpl(ClaimAttachmentRepository claimAttachmentRepository, ClaimAttachmentMapper claimAttachmentMapper) {
        this.claimAttachmentRepository = claimAttachmentRepository;
        this.claimAttachmentMapper = claimAttachmentMapper;
    }

    /**
     * Save a claimAttachment.
     *
     * @param claimAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimAttachmentDTO save(ClaimAttachmentDTO claimAttachmentDTO) {
        log.debug("Request to save ClaimAttachment : {}", claimAttachmentDTO);
        ClaimAttachment claimAttachment = claimAttachmentMapper.toEntity(claimAttachmentDTO);
        claimAttachment = claimAttachmentRepository.save(claimAttachment);
        return claimAttachmentMapper.toDto(claimAttachment);
    }

    /**
     * Get all the claimAttachments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimAttachmentDTO> findAll() {
        log.debug("Request to get all ClaimAttachments");
        return claimAttachmentRepository.findAll().stream()
            .map(claimAttachmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimAttachmentDTO> findOne(Long id) {
        log.debug("Request to get ClaimAttachment : {}", id);
        return claimAttachmentRepository.findById(id)
            .map(claimAttachmentMapper::toDto);
    }

    /**
     * Delete the claimAttachment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimAttachment : {}", id);
        claimAttachmentRepository.deleteById(id);
    }
}
