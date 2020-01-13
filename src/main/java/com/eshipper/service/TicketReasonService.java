package com.eshipper.service;

import com.eshipper.service.dto.TicketReasonDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.TicketReason}.
 */
public interface TicketReasonService {

    /**
     * Save a ticketReason.
     *
     * @param ticketReasonDTO the entity to save.
     * @return the persisted entity.
     */
    TicketReasonDTO save(TicketReasonDTO ticketReasonDTO);

    /**
     * Get all the ticketReasons.
     *
     * @return the list of entities.
     */
    List<TicketReasonDTO> findAll();


    /**
     * Get the "id" ticketReason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketReasonDTO> findOne(Long id);

    /**
     * Delete the "id" ticketReason.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
