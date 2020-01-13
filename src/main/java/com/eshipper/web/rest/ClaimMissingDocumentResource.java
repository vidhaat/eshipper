package com.eshipper.web.rest;

import com.eshipper.service.ClaimMissingDocumentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimMissingDocumentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimMissingDocument}.
 */
@RestController
@RequestMapping("/api")
public class ClaimMissingDocumentResource {

    private final Logger log = LoggerFactory.getLogger(ClaimMissingDocumentResource.class);

    private static final String ENTITY_NAME = "claimMissingDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimMissingDocumentService claimMissingDocumentService;

    public ClaimMissingDocumentResource(ClaimMissingDocumentService claimMissingDocumentService) {
        this.claimMissingDocumentService = claimMissingDocumentService;
    }

    /**
     * {@code POST  /claim-missing-documents} : Create a new claimMissingDocument.
     *
     * @param claimMissingDocumentDTO the claimMissingDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimMissingDocumentDTO, or with status {@code 400 (Bad Request)} if the claimMissingDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-missing-documents")
    public ResponseEntity<ClaimMissingDocumentDTO> createClaimMissingDocument(@RequestBody ClaimMissingDocumentDTO claimMissingDocumentDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimMissingDocument : {}", claimMissingDocumentDTO);
        if (claimMissingDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimMissingDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimMissingDocumentDTO result = claimMissingDocumentService.save(claimMissingDocumentDTO);
        return ResponseEntity.created(new URI("/api/claim-missing-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-missing-documents} : Updates an existing claimMissingDocument.
     *
     * @param claimMissingDocumentDTO the claimMissingDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimMissingDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the claimMissingDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimMissingDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-missing-documents")
    public ResponseEntity<ClaimMissingDocumentDTO> updateClaimMissingDocument(@RequestBody ClaimMissingDocumentDTO claimMissingDocumentDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimMissingDocument : {}", claimMissingDocumentDTO);
        if (claimMissingDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimMissingDocumentDTO result = claimMissingDocumentService.save(claimMissingDocumentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimMissingDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-missing-documents} : get all the claimMissingDocuments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimMissingDocuments in body.
     */
    @GetMapping("/claim-missing-documents")
    public List<ClaimMissingDocumentDTO> getAllClaimMissingDocuments() {
        log.debug("REST request to get all ClaimMissingDocuments");
        return claimMissingDocumentService.findAll();
    }

    /**
     * {@code GET  /claim-missing-documents/:id} : get the "id" claimMissingDocument.
     *
     * @param id the id of the claimMissingDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimMissingDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-missing-documents/{id}")
    public ResponseEntity<ClaimMissingDocumentDTO> getClaimMissingDocument(@PathVariable Long id) {
        log.debug("REST request to get ClaimMissingDocument : {}", id);
        Optional<ClaimMissingDocumentDTO> claimMissingDocumentDTO = claimMissingDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimMissingDocumentDTO);
    }

    /**
     * {@code DELETE  /claim-missing-documents/:id} : delete the "id" claimMissingDocument.
     *
     * @param id the id of the claimMissingDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-missing-documents/{id}")
    public ResponseEntity<Void> deleteClaimMissingDocument(@PathVariable Long id) {
        log.debug("REST request to delete ClaimMissingDocument : {}", id);
        claimMissingDocumentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
