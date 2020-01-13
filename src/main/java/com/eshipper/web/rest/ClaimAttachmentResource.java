package com.eshipper.web.rest;

import com.eshipper.service.ClaimAttachmentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimAttachmentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimAttachment}.
 */
@RestController
@RequestMapping("/api")
public class ClaimAttachmentResource {

    private final Logger log = LoggerFactory.getLogger(ClaimAttachmentResource.class);

    private static final String ENTITY_NAME = "claimAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimAttachmentService claimAttachmentService;

    public ClaimAttachmentResource(ClaimAttachmentService claimAttachmentService) {
        this.claimAttachmentService = claimAttachmentService;
    }

    /**
     * {@code POST  /claim-attachments} : Create a new claimAttachment.
     *
     * @param claimAttachmentDTO the claimAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimAttachmentDTO, or with status {@code 400 (Bad Request)} if the claimAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-attachments")
    public ResponseEntity<ClaimAttachmentDTO> createClaimAttachment(@RequestBody ClaimAttachmentDTO claimAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimAttachment : {}", claimAttachmentDTO);
        if (claimAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimAttachmentDTO result = claimAttachmentService.save(claimAttachmentDTO);
        return ResponseEntity.created(new URI("/api/claim-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-attachments} : Updates an existing claimAttachment.
     *
     * @param claimAttachmentDTO the claimAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the claimAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-attachments")
    public ResponseEntity<ClaimAttachmentDTO> updateClaimAttachment(@RequestBody ClaimAttachmentDTO claimAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimAttachment : {}", claimAttachmentDTO);
        if (claimAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimAttachmentDTO result = claimAttachmentService.save(claimAttachmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimAttachmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-attachments} : get all the claimAttachments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimAttachments in body.
     */
    @GetMapping("/claim-attachments")
    public List<ClaimAttachmentDTO> getAllClaimAttachments() {
        log.debug("REST request to get all ClaimAttachments");
        return claimAttachmentService.findAll();
    }

    /**
     * {@code GET  /claim-attachments/:id} : get the "id" claimAttachment.
     *
     * @param id the id of the claimAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-attachments/{id}")
    public ResponseEntity<ClaimAttachmentDTO> getClaimAttachment(@PathVariable Long id) {
        log.debug("REST request to get ClaimAttachment : {}", id);
        Optional<ClaimAttachmentDTO> claimAttachmentDTO = claimAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimAttachmentDTO);
    }

    /**
     * {@code DELETE  /claim-attachments/:id} : delete the "id" claimAttachment.
     *
     * @param id the id of the claimAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-attachments/{id}")
    public ResponseEntity<Void> deleteClaimAttachment(@PathVariable Long id) {
        log.debug("REST request to delete ClaimAttachment : {}", id);
        claimAttachmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
