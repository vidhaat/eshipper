package com.eshipper.service.impl;

import com.eshipper.service.ClaimDocumentTypeService;
import com.eshipper.domain.ClaimDocumentType;
import com.eshipper.repository.ClaimDocumentTypeRepository;
import com.eshipper.service.dto.ClaimDocumentTypeDTO;
import com.eshipper.service.mapper.ClaimDocumentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClaimDocumentType}.
 */
@Service
@Transactional
public class ClaimDocumentTypeServiceImpl implements ClaimDocumentTypeService {

    private final Logger log = LoggerFactory.getLogger(ClaimDocumentTypeServiceImpl.class);

    private final ClaimDocumentTypeRepository claimDocumentTypeRepository;

    private final ClaimDocumentTypeMapper claimDocumentTypeMapper;

    public ClaimDocumentTypeServiceImpl(ClaimDocumentTypeRepository claimDocumentTypeRepository, ClaimDocumentTypeMapper claimDocumentTypeMapper) {
        this.claimDocumentTypeRepository = claimDocumentTypeRepository;
        this.claimDocumentTypeMapper = claimDocumentTypeMapper;
    }

    /**
     * Save a claimDocumentType.
     *
     * @param claimDocumentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimDocumentTypeDTO save(ClaimDocumentTypeDTO claimDocumentTypeDTO) {
        log.debug("Request to save ClaimDocumentType : {}", claimDocumentTypeDTO);
        ClaimDocumentType claimDocumentType = claimDocumentTypeMapper.toEntity(claimDocumentTypeDTO);
        claimDocumentType = claimDocumentTypeRepository.save(claimDocumentType);
        return claimDocumentTypeMapper.toDto(claimDocumentType);
    }

    /**
     * Get all the claimDocumentTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClaimDocumentTypeDTO> findAll() {
        log.debug("Request to get all ClaimDocumentTypes");
        return claimDocumentTypeRepository.findAll().stream()
            .map(claimDocumentTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one claimDocumentType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimDocumentTypeDTO> findOne(Long id) {
        log.debug("Request to get ClaimDocumentType : {}", id);
        return claimDocumentTypeRepository.findById(id)
            .map(claimDocumentTypeMapper::toDto);
    }

    /**
     * Delete the claimDocumentType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimDocumentType : {}", id);
        claimDocumentTypeRepository.deleteById(id);
    }
}
