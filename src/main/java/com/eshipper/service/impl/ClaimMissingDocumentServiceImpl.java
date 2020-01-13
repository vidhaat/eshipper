package com.eshipper.service.impl;

import com.eshipper.service.ClaimMissingDocumentService;
import com.eshipper.domain.ClaimMissingDocument;
import com.eshipper.repository.ClaimMissingDocumentRepository;
import com.eshipper.service.dto.ClaimMissingDocumentDTO;
import com.eshipper.service.mapper.ClaimMissingDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimMissingDocument}.
 */
@Service
@Transactional
public class ClaimMissingDocumentServiceImpl implements ClaimMissingDocumentService {

    private final Logger log = LoggerFactory.getLogger(ClaimMissingDocumentServiceImpl.class);

    private final ClaimMissingDocumentRepository claimMissingDocumentRepository;

    private final ClaimMissingDocumentMapper claimMissingDocumentMapper;

    public ClaimMissingDocumentServiceImpl(ClaimMissingDocumentRepository claimMissingDocumentRepository, ClaimMissingDocumentMapper claimMissingDocumentMapper) {
        this.claimMissingDocumentRepository = claimMissingDocumentRepository;
        this.claimMissingDocumentMapper = claimMissingDocumentMapper;
    }

    /**
     * Save a claimMissingDocument.
     *
     * @param claimMissingDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimMissingDocumentDTO save(ClaimMissingDocumentDTO claimMissingDocumentDTO) {
        log.debug("Request to save ClaimMissingDocument : {}", claimMissingDocumentDTO);
        ClaimMissingDocument claimMissingDocument = claimMissingDocumentMapper.toEntity(claimMissingDocumentDTO);
        claimMissingDocument = claimMissingDocumentRepository.save(claimMissingDocument);
        return claimMissingDocumentMapper.toDto(claimMissingDocument);
    }

    /**
     * Get all the claimMissingDocuments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimMissingDocumentDTO> findAll() {
        log.debug("Request to get all ClaimMissingDocuments");
        return claimMissingDocumentRepository.findAll().stream()
            .map(claimMissingDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimMissingDocument by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimMissingDocumentDTO> findOne(Long id) {
        log.debug("Request to get ClaimMissingDocument : {}", id);
        return claimMissingDocumentRepository.findById(id)
            .map(claimMissingDocumentMapper::toDto);
    }

    /**
     * Delete the claimMissingDocument by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimMissingDocument : {}", id);
        claimMissingDocumentRepository.deleteById(id);
    }
}
