package com.eshipper.service.impl;

import com.eshipper.service.TicketReasonService;
import com.eshipper.domain.TicketReason;
import com.eshipper.repository.TicketReasonRepository;
import com.eshipper.service.dto.TicketReasonDTO;
import com.eshipper.service.mapper.TicketReasonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TicketReason}.
 */
@Service
@Transactional
public class TicketReasonServiceImpl implements TicketReasonService {

    private final Logger log = LoggerFactory.getLogger(TicketReasonServiceImpl.class);

    private final TicketReasonRepository ticketReasonRepository;

    private final TicketReasonMapper ticketReasonMapper;

    public TicketReasonServiceImpl(TicketReasonRepository ticketReasonRepository, TicketReasonMapper ticketReasonMapper) {
        this.ticketReasonRepository = ticketReasonRepository;
        this.ticketReasonMapper = ticketReasonMapper;
    }

    /**
     * Save a ticketReason.
     *
     * @param ticketReasonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TicketReasonDTO save(TicketReasonDTO ticketReasonDTO) {
        log.debug("Request to save TicketReason : {}", ticketReasonDTO);
        TicketReason ticketReason = ticketReasonMapper.toEntity(ticketReasonDTO);
        ticketReason = ticketReasonRepository.save(ticketReason);
        return ticketReasonMapper.toDto(ticketReason);
    }

    /**
     * Get all the ticketReasons.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TicketReasonDTO> findAll() {
        log.debug("Request to get all TicketReasons");
        return ticketReasonRepository.findAll().stream()
            .map(ticketReasonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ticketReason by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TicketReasonDTO> findOne(Long id) {
        log.debug("Request to get TicketReason : {}", id);
        return ticketReasonRepository.findById(id)
            .map(ticketReasonMapper::toDto);
    }

    /**
     * Delete the ticketReason by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TicketReason : {}", id);
        ticketReasonRepository.deleteById(id);
    }
}
