package com.eshipper.web.rest;

import com.eshipper.service.ClaimEshipperRefundService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimEshipperRefundDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimEshipperRefund}.
 */
@RestController
@RequestMapping("/api")
public class ClaimEshipperRefundResource {

    private final Logger log = LoggerFactory.getLogger(ClaimEshipperRefundResource.class);

    private static final String ENTITY_NAME = "claimEshipperRefund";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimEshipperRefundService claimEshipperRefundService;

    public ClaimEshipperRefundResource(ClaimEshipperRefundService claimEshipperRefundService) {
        this.claimEshipperRefundService = claimEshipperRefundService;
    }

    /**
     * {@code POST  /claim-eshipper-refunds} : Create a new claimEshipperRefund.
     *
     * @param claimEshipperRefundDTO the claimEshipperRefundDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimEshipperRefundDTO, or with status {@code 400 (Bad Request)} if the claimEshipperRefund has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-eshipper-refunds")
    public ResponseEntity<ClaimEshipperRefundDTO> createClaimEshipperRefund(@RequestBody ClaimEshipperRefundDTO claimEshipperRefundDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimEshipperRefund : {}", claimEshipperRefundDTO);
        if (claimEshipperRefundDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimEshipperRefund cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimEshipperRefundDTO result = claimEshipperRefundService.save(claimEshipperRefundDTO);
        return ResponseEntity.created(new URI("/api/claim-eshipper-refunds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-eshipper-refunds} : Updates an existing claimEshipperRefund.
     *
     * @param claimEshipperRefundDTO the claimEshipperRefundDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimEshipperRefundDTO,
     * or with status {@code 400 (Bad Request)} if the claimEshipperRefundDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimEshipperRefundDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-eshipper-refunds")
    public ResponseEntity<ClaimEshipperRefundDTO> updateClaimEshipperRefund(@RequestBody ClaimEshipperRefundDTO claimEshipperRefundDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimEshipperRefund : {}", claimEshipperRefundDTO);
        if (claimEshipperRefundDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimEshipperRefundDTO result = claimEshipperRefundService.save(claimEshipperRefundDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimEshipperRefundDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-eshipper-refunds} : get all the claimEshipperRefunds.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimEshipperRefunds in body.
     */
    @GetMapping("/claim-eshipper-refunds")
    public List<ClaimEshipperRefundDTO> getAllClaimEshipperRefunds() {
        log.debug("REST request to get all ClaimEshipperRefunds");
        return claimEshipperRefundService.findAll();
    }

    /**
     * {@code GET  /claim-eshipper-refunds/:id} : get the "id" claimEshipperRefund.
     *
     * @param id the id of the claimEshipperRefundDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimEshipperRefundDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-eshipper-refunds/{id}")
    public ResponseEntity<ClaimEshipperRefundDTO> getClaimEshipperRefund(@PathVariable Long id) {
        log.debug("REST request to get ClaimEshipperRefund : {}", id);
        Optional<ClaimEshipperRefundDTO> claimEshipperRefundDTO = claimEshipperRefundService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimEshipperRefundDTO);
    }

    /**
     * {@code DELETE  /claim-eshipper-refunds/:id} : delete the "id" claimEshipperRefund.
     *
     * @param id the id of the claimEshipperRefundDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-eshipper-refunds/{id}")
    public ResponseEntity<Void> deleteClaimEshipperRefund(@PathVariable Long id) {
        log.debug("REST request to delete ClaimEshipperRefund : {}", id);
        claimEshipperRefundService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
