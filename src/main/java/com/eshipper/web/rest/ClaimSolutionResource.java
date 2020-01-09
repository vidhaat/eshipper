package com.eshipper.web.rest;

import com.eshipper.service.ClaimSolutionService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimSolutionDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimSolution}.
 */
@RestController
@RequestMapping("/api")
public class ClaimSolutionResource {

    private final Logger log = LoggerFactory.getLogger(ClaimSolutionResource.class);

    private static final String ENTITY_NAME = "claimSolution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimSolutionService claimSolutionService;

    public ClaimSolutionResource(ClaimSolutionService claimSolutionService) {
        this.claimSolutionService = claimSolutionService;
    }

    /**
     * {@code POST  /claim-solutions} : Create a new claimSolution.
     *
     * @param claimSolutionDTO the claimSolutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimSolutionDTO, or with status {@code 400 (Bad Request)} if the claimSolution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-solutions")
    public ResponseEntity<ClaimSolutionDTO> createClaimSolution(@RequestBody ClaimSolutionDTO claimSolutionDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimSolution : {}", claimSolutionDTO);
        if (claimSolutionDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimSolution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimSolutionDTO result = claimSolutionService.save(claimSolutionDTO);
        return ResponseEntity.created(new URI("/api/claim-solutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-solutions} : Updates an existing claimSolution.
     *
     * @param claimSolutionDTO the claimSolutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimSolutionDTO,
     * or with status {@code 400 (Bad Request)} if the claimSolutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimSolutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-solutions")
    public ResponseEntity<ClaimSolutionDTO> updateClaimSolution(@RequestBody ClaimSolutionDTO claimSolutionDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimSolution : {}", claimSolutionDTO);
        if (claimSolutionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimSolutionDTO result = claimSolutionService.save(claimSolutionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimSolutionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-solutions} : get all the claimSolutions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimSolutions in body.
     */
    @GetMapping("/claim-solutions")
    public List<ClaimSolutionDTO> getAllClaimSolutions() {
        log.debug("REST request to get all ClaimSolutions");
        return claimSolutionService.findAll();
    }

    /**
     * {@code GET  /claim-solutions/:id} : get the "id" claimSolution.
     *
     * @param id the id of the claimSolutionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimSolutionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-solutions/{id}")
    public ResponseEntity<ClaimSolutionDTO> getClaimSolution(@PathVariable Long id) {
        log.debug("REST request to get ClaimSolution : {}", id);
        Optional<ClaimSolutionDTO> claimSolutionDTO = claimSolutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimSolutionDTO);
    }

    /**
     * {@code DELETE  /claim-solutions/:id} : delete the "id" claimSolution.
     *
     * @param id the id of the claimSolutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-solutions/{id}")
    public ResponseEntity<Void> deleteClaimSolution(@PathVariable Long id) {
        log.debug("REST request to delete ClaimSolution : {}", id);
        claimSolutionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
