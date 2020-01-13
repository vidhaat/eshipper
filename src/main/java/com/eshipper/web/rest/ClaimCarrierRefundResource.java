package com.eshipper.web.rest;

import com.eshipper.service.ClaimCarrierRefundService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimCarrierRefundDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimCarrierRefund}.
 */
@RestController
@RequestMapping("/api")
public class ClaimCarrierRefundResource {

    private final Logger log = LoggerFactory.getLogger(ClaimCarrierRefundResource.class);

    private static final String ENTITY_NAME = "claimCarrierRefund";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimCarrierRefundService claimCarrierRefundService;

    public ClaimCarrierRefundResource(ClaimCarrierRefundService claimCarrierRefundService) {
        this.claimCarrierRefundService = claimCarrierRefundService;
    }

    /**
     * {@code POST  /claim-carrier-refunds} : Create a new claimCarrierRefund.
     *
     * @param claimCarrierRefundDTO the claimCarrierRefundDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimCarrierRefundDTO, or with status {@code 400 (Bad Request)} if the claimCarrierRefund has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-carrier-refunds")
    public ResponseEntity<ClaimCarrierRefundDTO> createClaimCarrierRefund(@RequestBody ClaimCarrierRefundDTO claimCarrierRefundDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimCarrierRefund : {}", claimCarrierRefundDTO);
        if (claimCarrierRefundDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimCarrierRefund cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimCarrierRefundDTO result = claimCarrierRefundService.save(claimCarrierRefundDTO);
        return ResponseEntity.created(new URI("/api/claim-carrier-refunds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-carrier-refunds} : Updates an existing claimCarrierRefund.
     *
     * @param claimCarrierRefundDTO the claimCarrierRefundDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimCarrierRefundDTO,
     * or with status {@code 400 (Bad Request)} if the claimCarrierRefundDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimCarrierRefundDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-carrier-refunds")
    public ResponseEntity<ClaimCarrierRefundDTO> updateClaimCarrierRefund(@RequestBody ClaimCarrierRefundDTO claimCarrierRefundDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimCarrierRefund : {}", claimCarrierRefundDTO);
        if (claimCarrierRefundDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimCarrierRefundDTO result = claimCarrierRefundService.save(claimCarrierRefundDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimCarrierRefundDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-carrier-refunds} : get all the claimCarrierRefunds.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimCarrierRefunds in body.
     */
    @GetMapping("/claim-carrier-refunds")
    public List<ClaimCarrierRefundDTO> getAllClaimCarrierRefunds() {
        log.debug("REST request to get all ClaimCarrierRefunds");
        return claimCarrierRefundService.findAll();
    }

    /**
     * {@code GET  /claim-carrier-refunds/:id} : get the "id" claimCarrierRefund.
     *
     * @param id the id of the claimCarrierRefundDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimCarrierRefundDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-carrier-refunds/{id}")
    public ResponseEntity<ClaimCarrierRefundDTO> getClaimCarrierRefund(@PathVariable Long id) {
        log.debug("REST request to get ClaimCarrierRefund : {}", id);
        Optional<ClaimCarrierRefundDTO> claimCarrierRefundDTO = claimCarrierRefundService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimCarrierRefundDTO);
    }

    /**
     * {@code DELETE  /claim-carrier-refunds/:id} : delete the "id" claimCarrierRefund.
     *
     * @param id the id of the claimCarrierRefundDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-carrier-refunds/{id}")
    public ResponseEntity<Void> deleteClaimCarrierRefund(@PathVariable Long id) {
        log.debug("REST request to delete ClaimCarrierRefund : {}", id);
        claimCarrierRefundService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
