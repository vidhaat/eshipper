package com.eshipper.web.rest;

import com.eshipper.service.ClaimCommentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimCommentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimComment}.
 */
@RestController
@RequestMapping("/api")
public class ClaimCommentResource {

    private final Logger log = LoggerFactory.getLogger(ClaimCommentResource.class);

    private static final String ENTITY_NAME = "claimComment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimCommentService claimCommentService;

    public ClaimCommentResource(ClaimCommentService claimCommentService) {
        this.claimCommentService = claimCommentService;
    }

    /**
     * {@code POST  /claim-comments} : Create a new claimComment.
     *
     * @param claimCommentDTO the claimCommentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimCommentDTO, or with status {@code 400 (Bad Request)} if the claimComment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-comments")
    public ResponseEntity<ClaimCommentDTO> createClaimComment(@RequestBody ClaimCommentDTO claimCommentDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimComment : {}", claimCommentDTO);
        if (claimCommentDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimCommentDTO result = claimCommentService.save(claimCommentDTO);
        return ResponseEntity.created(new URI("/api/claim-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-comments} : Updates an existing claimComment.
     *
     * @param claimCommentDTO the claimCommentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimCommentDTO,
     * or with status {@code 400 (Bad Request)} if the claimCommentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimCommentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-comments")
    public ResponseEntity<ClaimCommentDTO> updateClaimComment(@RequestBody ClaimCommentDTO claimCommentDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimComment : {}", claimCommentDTO);
        if (claimCommentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimCommentDTO result = claimCommentService.save(claimCommentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimCommentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-comments} : get all the claimComments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimComments in body.
     */
    @GetMapping("/claim-comments")
    public List<ClaimCommentDTO> getAllClaimComments() {
        log.debug("REST request to get all ClaimComments");
        return claimCommentService.findAll();
    }

    /**
     * {@code GET  /claim-comments/:id} : get the "id" claimComment.
     *
     * @param id the id of the claimCommentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimCommentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-comments/{id}")
    public ResponseEntity<ClaimCommentDTO> getClaimComment(@PathVariable Long id) {
        log.debug("REST request to get ClaimComment : {}", id);
        Optional<ClaimCommentDTO> claimCommentDTO = claimCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimCommentDTO);
    }

    /**
     * {@code DELETE  /claim-comments/:id} : delete the "id" claimComment.
     *
     * @param id the id of the claimCommentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-comments/{id}")
    public ResponseEntity<Void> deleteClaimComment(@PathVariable Long id) {
        log.debug("REST request to delete ClaimComment : {}", id);
        claimCommentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
