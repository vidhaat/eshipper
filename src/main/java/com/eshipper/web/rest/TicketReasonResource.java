package com.eshipper.web.rest;

import com.eshipper.service.TicketReasonService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.TicketReasonDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.TicketReason}.
 */
@RestController
@RequestMapping("/api")
public class TicketReasonResource {

    private final Logger log = LoggerFactory.getLogger(TicketReasonResource.class);

    private static final String ENTITY_NAME = "ticketReason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketReasonService ticketReasonService;

    public TicketReasonResource(TicketReasonService ticketReasonService) {
        this.ticketReasonService = ticketReasonService;
    }

    /**
     * {@code POST  /ticket-reasons} : Create a new ticketReason.
     *
     * @param ticketReasonDTO the ticketReasonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketReasonDTO, or with status {@code 400 (Bad Request)} if the ticketReason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-reasons")
    public ResponseEntity<TicketReasonDTO> createTicketReason(@RequestBody TicketReasonDTO ticketReasonDTO) throws URISyntaxException {
        log.debug("REST request to save TicketReason : {}", ticketReasonDTO);
        if (ticketReasonDTO.getId() != null) {
            throw new BadRequestAlertException("A new ticketReason cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketReasonDTO result = ticketReasonService.save(ticketReasonDTO);
        return ResponseEntity.created(new URI("/api/ticket-reasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-reasons} : Updates an existing ticketReason.
     *
     * @param ticketReasonDTO the ticketReasonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketReasonDTO,
     * or with status {@code 400 (Bad Request)} if the ticketReasonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketReasonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-reasons")
    public ResponseEntity<TicketReasonDTO> updateTicketReason(@RequestBody TicketReasonDTO ticketReasonDTO) throws URISyntaxException {
        log.debug("REST request to update TicketReason : {}", ticketReasonDTO);
        if (ticketReasonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TicketReasonDTO result = ticketReasonService.save(ticketReasonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketReasonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ticket-reasons} : get all the ticketReasons.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketReasons in body.
     */
    @GetMapping("/ticket-reasons")
    public List<TicketReasonDTO> getAllTicketReasons() {
        log.debug("REST request to get all TicketReasons");
        return ticketReasonService.findAll();
    }

    /**
     * {@code GET  /ticket-reasons/:id} : get the "id" ticketReason.
     *
     * @param id the id of the ticketReasonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketReasonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-reasons/{id}")
    public ResponseEntity<TicketReasonDTO> getTicketReason(@PathVariable Long id) {
        log.debug("REST request to get TicketReason : {}", id);
        Optional<TicketReasonDTO> ticketReasonDTO = ticketReasonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketReasonDTO);
    }

    /**
     * {@code DELETE  /ticket-reasons/:id} : delete the "id" ticketReason.
     *
     * @param id the id of the ticketReasonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-reasons/{id}")
    public ResponseEntity<Void> deleteTicketReason(@PathVariable Long id) {
        log.debug("REST request to delete TicketReason : {}", id);
        ticketReasonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
