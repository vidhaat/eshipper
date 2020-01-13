package com.eshipper.web.rest;

import com.eshipper.service.ClaimStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimStatus}.
 */
@RestController
@RequestMapping("/api")
public class ClaimStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimStatusResource.class);

    private static final String ENTITY_NAME = "claimStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimStatusService claimStatusService;

    public ClaimStatusResource(ClaimStatusService claimStatusService) {
        this.claimStatusService = claimStatusService;
    }

    /**
     * {@code POST  /claim-statuses} : Create a new claimStatus.
     *
     * @param claimStatusDTO the claimStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimStatusDTO, or with status {@code 400 (Bad Request)} if the claimStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-statuses")
    public ResponseEntity<ClaimStatusDTO> createClaimStatus(@RequestBody ClaimStatusDTO claimStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimStatus : {}", claimStatusDTO);
        if (claimStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimStatusDTO result = claimStatusService.save(claimStatusDTO);
        return ResponseEntity.created(new URI("/api/claim-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-statuses} : Updates an existing claimStatus.
     *
     * @param claimStatusDTO the claimStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-statuses")
    public ResponseEntity<ClaimStatusDTO> updateClaimStatus(@RequestBody ClaimStatusDTO claimStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimStatus : {}", claimStatusDTO);
        if (claimStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimStatusDTO result = claimStatusService.save(claimStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-statuses} : get all the claimStatuses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimStatuses in body.
     */
    @GetMapping("/claim-statuses")
    public List<ClaimStatusDTO> getAllClaimStatuses() {
        log.debug("REST request to get all ClaimStatuses");
        return claimStatusService.findAll();
    }

    /**
     * {@code GET  /claim-statuses/:id} : get the "id" claimStatus.
     *
     * @param id the id of the claimStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-statuses/{id}")
    public ResponseEntity<ClaimStatusDTO> getClaimStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimStatus : {}", id);
        Optional<ClaimStatusDTO> claimStatusDTO = claimStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimStatusDTO);
    }

    /**
     * {@code DELETE  /claim-statuses/:id} : delete the "id" claimStatus.
     *
     * @param id the id of the claimStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-statuses/{id}")
    public ResponseEntity<Void> deleteClaimStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimStatus : {}", id);
        claimStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
