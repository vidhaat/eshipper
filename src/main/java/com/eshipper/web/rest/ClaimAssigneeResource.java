package com.eshipper.web.rest;

import com.eshipper.service.ClaimAssigneeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimAssigneeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimAssignee}.
 */
@RestController
@RequestMapping("/api")
public class ClaimAssigneeResource {

    private final Logger log = LoggerFactory.getLogger(ClaimAssigneeResource.class);

    private static final String ENTITY_NAME = "claimAssignee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimAssigneeService claimAssigneeService;

    public ClaimAssigneeResource(ClaimAssigneeService claimAssigneeService) {
        this.claimAssigneeService = claimAssigneeService;
    }

    /**
     * {@code POST  /claim-assignees} : Create a new claimAssignee.
     *
     * @param claimAssigneeDTO the claimAssigneeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimAssigneeDTO, or with status {@code 400 (Bad Request)} if the claimAssignee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-assignees")
    public ResponseEntity<ClaimAssigneeDTO> createClaimAssignee(@RequestBody ClaimAssigneeDTO claimAssigneeDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimAssignee : {}", claimAssigneeDTO);
        if (claimAssigneeDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimAssignee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimAssigneeDTO result = claimAssigneeService.save(claimAssigneeDTO);
        return ResponseEntity.created(new URI("/api/claim-assignees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-assignees} : Updates an existing claimAssignee.
     *
     * @param claimAssigneeDTO the claimAssigneeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimAssigneeDTO,
     * or with status {@code 400 (Bad Request)} if the claimAssigneeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimAssigneeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-assignees")
    public ResponseEntity<ClaimAssigneeDTO> updateClaimAssignee(@RequestBody ClaimAssigneeDTO claimAssigneeDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimAssignee : {}", claimAssigneeDTO);
        if (claimAssigneeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimAssigneeDTO result = claimAssigneeService.save(claimAssigneeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimAssigneeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-assignees} : get all the claimAssignees.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimAssignees in body.
     */
    @GetMapping("/claim-assignees")
    public List<ClaimAssigneeDTO> getAllClaimAssignees() {
        log.debug("REST request to get all ClaimAssignees");
        return claimAssigneeService.findAll();
    }

    /**
     * {@code GET  /claim-assignees/:id} : get the "id" claimAssignee.
     *
     * @param id the id of the claimAssigneeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimAssigneeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-assignees/{id}")
    public ResponseEntity<ClaimAssigneeDTO> getClaimAssignee(@PathVariable Long id) {
        log.debug("REST request to get ClaimAssignee : {}", id);
        Optional<ClaimAssigneeDTO> claimAssigneeDTO = claimAssigneeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimAssigneeDTO);
    }

    /**
     * {@code DELETE  /claim-assignees/:id} : delete the "id" claimAssignee.
     *
     * @param id the id of the claimAssigneeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-assignees/{id}")
    public ResponseEntity<Void> deleteClaimAssignee(@PathVariable Long id) {
        log.debug("REST request to delete ClaimAssignee : {}", id);
        claimAssigneeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
