package com.eshipper.web.rest;

import com.eshipper.service.ElasticShippingClaimService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticShippingClaimDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ElasticShippingClaim}.
 */
@RestController
@RequestMapping("/api")
public class ElasticShippingClaimResource {

    private final Logger log = LoggerFactory.getLogger(ElasticShippingClaimResource.class);

    private static final String ENTITY_NAME = "elasticShippingClaim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticShippingClaimService elasticShippingClaimService;

    public ElasticShippingClaimResource(ElasticShippingClaimService elasticShippingClaimService) {
        this.elasticShippingClaimService = elasticShippingClaimService;
    }

    /**
     * {@code POST  /elastic-shipping-claims} : Create a new elasticShippingClaim.
     *
     * @param elasticShippingClaimDTO the elasticShippingClaimDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticShippingClaimDTO, or with status {@code 400 (Bad Request)} if the elasticShippingClaim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elastic-shipping-claims")
    public ResponseEntity<ElasticShippingClaimDTO> createElasticShippingClaim(@RequestBody ElasticShippingClaimDTO elasticShippingClaimDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticShippingClaim : {}", elasticShippingClaimDTO);
        if (elasticShippingClaimDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticShippingClaim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticShippingClaimDTO result = elasticShippingClaimService.save(elasticShippingClaimDTO);
        return ResponseEntity.created(new URI("/api/elastic-shipping-claims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elastic-shipping-claims} : Updates an existing elasticShippingClaim.
     *
     * @param elasticShippingClaimDTO the elasticShippingClaimDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticShippingClaimDTO,
     * or with status {@code 400 (Bad Request)} if the elasticShippingClaimDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticShippingClaimDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elastic-shipping-claims")
    public ResponseEntity<ElasticShippingClaimDTO> updateElasticShippingClaim(@RequestBody ElasticShippingClaimDTO elasticShippingClaimDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticShippingClaim : {}", elasticShippingClaimDTO);
        if (elasticShippingClaimDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticShippingClaimDTO result = elasticShippingClaimService.save(elasticShippingClaimDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticShippingClaimDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elastic-shipping-claims} : get all the elasticShippingClaims.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticShippingClaims in body.
     */
    @GetMapping("/elastic-shipping-claims")
    public List<ElasticShippingClaimDTO> getAllElasticShippingClaims() {
        log.debug("REST request to get all ElasticShippingClaims");
        return elasticShippingClaimService.findAll();
    }

    /**
     * {@code GET  /elastic-shipping-claims/:id} : get the "id" elasticShippingClaim.
     *
     * @param id the id of the elasticShippingClaimDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticShippingClaimDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elastic-shipping-claims/{id}")
    public ResponseEntity<ElasticShippingClaimDTO> getElasticShippingClaim(@PathVariable Long id) {
        log.debug("REST request to get ElasticShippingClaim : {}", id);
        Optional<ElasticShippingClaimDTO> elasticShippingClaimDTO = elasticShippingClaimService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticShippingClaimDTO);
    }

    /**
     * {@code DELETE  /elastic-shipping-claims/:id} : delete the "id" elasticShippingClaim.
     *
     * @param id the id of the elasticShippingClaimDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elastic-shipping-claims/{id}")
    public ResponseEntity<Void> deleteElasticShippingClaim(@PathVariable Long id) {
        log.debug("REST request to delete ElasticShippingClaim : {}", id);
        elasticShippingClaimService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
