package com.eshipper.service.impl;

import com.eshipper.service.ContactPreferenceService;
import com.eshipper.domain.ContactPreference;
import com.eshipper.repository.ContactPreferenceRepository;
import com.eshipper.service.dto.ContactPreferenceDTO;
import com.eshipper.service.mapper.ContactPreferenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ContactPreference}.
 */
@Service
@Transactional
public class ContactPreferenceServiceImpl implements ContactPreferenceService {

    private final Logger log = LoggerFactory.getLogger(ContactPreferenceServiceImpl.class);

    private final ContactPreferenceRepository contactPreferenceRepository;

    private final ContactPreferenceMapper contactPreferenceMapper;

    public ContactPreferenceServiceImpl(ContactPreferenceRepository contactPreferenceRepository, ContactPreferenceMapper contactPreferenceMapper) {
        this.contactPreferenceRepository = contactPreferenceRepository;
        this.contactPreferenceMapper = contactPreferenceMapper;
    }

    /**
     * Save a contactPreference.
     *
     * @param contactPreferenceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContactPreferenceDTO save(ContactPreferenceDTO contactPreferenceDTO) {
        log.debug("Request to save ContactPreference : {}", contactPreferenceDTO);
        ContactPreference contactPreference = contactPreferenceMapper.toEntity(contactPreferenceDTO);
        contactPreference = contactPreferenceRepository.save(contactPreference);
        return contactPreferenceMapper.toDto(contactPreference);
    }

    /**
     * Get all the contactPreferences.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactPreferenceDTO> findAll() {
        log.debug("Request to get all ContactPreferences");
        return contactPreferenceRepository.findAll().stream()
            .map(contactPreferenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contactPreference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactPreferenceDTO> findOne(Long id) {
        log.debug("Request to get ContactPreference : {}", id);
        return contactPreferenceRepository.findById(id)
            .map(contactPreferenceMapper::toDto);
    }

    /**
     * Delete the contactPreference by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactPreference : {}", id);
        contactPreferenceRepository.deleteById(id);
    }
}
