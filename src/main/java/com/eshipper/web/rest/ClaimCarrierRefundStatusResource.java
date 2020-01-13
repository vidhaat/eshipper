package com.eshipper.web.rest;

import com.eshipper.service.ClaimCarrierRefundStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimCarrierRefundStatus}.
 */
@RestController
@RequestMapping("/api")
public class ClaimCarrierRefundStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimCarrierRefundStatusResource.class);

    private static final String ENTITY_NAME = "claimCarrierRefundStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimCarrierRefundStatusService claimCarrierRefundStatusService;

    public ClaimCarrierRefundStatusResource(ClaimCarrierRefundStatusService claimCarrierRefundStatusService) {
        this.claimCarrierRefundStatusService = claimCarrierRefundStatusService;
    }

    /**
     * {@code POST  /claim-carrier-refund-statuses} : Create a new claimCarrierRefundStatus.
     *
     * @param claimCarrierRefundStatusDTO the claimCarrierRefundStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimCarrierRefundStatusDTO, or with status {@code 400 (Bad Request)} if the claimCarrierRefundStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-carrier-refund-statuses")
    public ResponseEntity<ClaimCarrierRefundStatusDTO> createClaimCarrierRefundStatus(@RequestBody ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimCarrierRefundStatus : {}", claimCarrierRefundStatusDTO);
        if (claimCarrierRefundStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimCarrierRefundStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimCarrierRefundStatusDTO result = claimCarrierRefundStatusService.save(claimCarrierRefundStatusDTO);
        return ResponseEntity.created(new URI("/api/claim-carrier-refund-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-carrier-refund-statuses} : Updates an existing claimCarrierRefundStatus.
     *
     * @param claimCarrierRefundStatusDTO the claimCarrierRefundStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimCarrierRefundStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimCarrierRefundStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimCarrierRefundStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-carrier-refund-statuses")
    public ResponseEntity<ClaimCarrierRefundStatusDTO> updateClaimCarrierRefundStatus(@RequestBody ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimCarrierRefundStatus : {}", claimCarrierRefundStatusDTO);
        if (claimCarrierRefundStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimCarrierRefundStatusDTO result = claimCarrierRefundStatusService.save(claimCarrierRefundStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimCarrierRefundStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-carrier-refund-statuses} : get all the claimCarrierRefundStatuses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimCarrierRefundStatuses in body.
     */
    @GetMapping("/claim-carrier-refund-statuses")
    public List<ClaimCarrierRefundStatusDTO> getAllClaimCarrierRefundStatuses() {
        log.debug("REST request to get all ClaimCarrierRefundStatuses");
        return claimCarrierRefundStatusService.findAll();
    }

    /**
     * {@code GET  /claim-carrier-refund-statuses/:id} : get the "id" claimCarrierRefundStatus.
     *
     * @param id the id of the claimCarrierRefundStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimCarrierRefundStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-carrier-refund-statuses/{id}")
    public ResponseEntity<ClaimCarrierRefundStatusDTO> getClaimCarrierRefundStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimCarrierRefundStatus : {}", id);
        Optional<ClaimCarrierRefundStatusDTO> claimCarrierRefundStatusDTO = claimCarrierRefundStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimCarrierRefundStatusDTO);
    }

    /**
     * {@code DELETE  /claim-carrier-refund-statuses/:id} : delete the "id" claimCarrierRefundStatus.
     *
     * @param id the id of the claimCarrierRefundStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-carrier-refund-statuses/{id}")
    public ResponseEntity<Void> deleteClaimCarrierRefundStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimCarrierRefundStatus : {}", id);
        claimCarrierRefundStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
