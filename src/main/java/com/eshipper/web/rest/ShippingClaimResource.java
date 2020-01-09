package com.eshipper.web.rest;

import com.eshipper.service.ShippingClaimService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ShippingClaimDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.ShippingClaim}.
 */
@RestController
@RequestMapping("/api")
public class ShippingClaimResource {

    private final Logger log = LoggerFactory.getLogger(ShippingClaimResource.class);

    private static final String ENTITY_NAME = "shippingClaim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShippingClaimService shippingClaimService;

    public ShippingClaimResource(ShippingClaimService shippingClaimService) {
        this.shippingClaimService = shippingClaimService;
    }

    /**
     * {@code POST  /shipping-claims} : Create a new shippingClaim.
     *
     * @param shippingClaimDTO the shippingClaimDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shippingClaimDTO, or with status {@code 400 (Bad Request)} if the shippingClaim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipping-claims")
    public ResponseEntity<ShippingClaimDTO> createShippingClaim(@RequestBody ShippingClaimDTO shippingClaimDTO) throws URISyntaxException {
        log.debug("REST request to save ShippingClaim : {}", shippingClaimDTO);
        if (shippingClaimDTO.getId() != null) {
            throw new BadRequestAlertException("A new shippingClaim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingClaimDTO result = shippingClaimService.save(shippingClaimDTO);
        return ResponseEntity.created(new URI("/api/shipping-claims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipping-claims} : Updates an existing shippingClaim.
     *
     * @param shippingClaimDTO the shippingClaimDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingClaimDTO,
     * or with status {@code 400 (Bad Request)} if the shippingClaimDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shippingClaimDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipping-claims")
    public ResponseEntity<ShippingClaimDTO> updateShippingClaim(@RequestBody ShippingClaimDTO shippingClaimDTO) throws URISyntaxException {
        log.debug("REST request to update ShippingClaim : {}", shippingClaimDTO);
        if (shippingClaimDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingClaimDTO result = shippingClaimService.save(shippingClaimDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shippingClaimDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shipping-claims} : get all the shippingClaims.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shippingClaims in body.
     */
    @GetMapping("/shipping-claims")
    public ResponseEntity<List<ShippingClaimDTO>> getAllShippingClaims(Pageable pageable) {
        log.debug("REST request to get a page of ShippingClaims");
        Page<ShippingClaimDTO> page = shippingClaimService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shipping-claims/:id} : get the "id" shippingClaim.
     *
     * @param id the id of the shippingClaimDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingClaimDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipping-claims/{id}")
    public ResponseEntity<ShippingClaimDTO> getShippingClaim(@PathVariable Long id) {
        log.debug("REST request to get ShippingClaim : {}", id);
        Optional<ShippingClaimDTO> shippingClaimDTO = shippingClaimService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingClaimDTO);
    }

    /**
     * {@code DELETE  /shipping-claims/:id} : delete the "id" shippingClaim.
     *
     * @param id the id of the shippingClaimDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipping-claims/{id}")
    public ResponseEntity<Void> deleteShippingClaim(@PathVariable Long id) {
        log.debug("REST request to delete ShippingClaim : {}", id);
        shippingClaimService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
