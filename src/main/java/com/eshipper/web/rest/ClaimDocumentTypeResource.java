package com.eshipper.web.rest;

import com.eshipper.service.ClaimDocumentTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ClaimDocumentTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ClaimDocumentType}.
 */
@RestController
@RequestMapping("/api")
public class ClaimDocumentTypeResource {

    private final Logger log = LoggerFactory.getLogger(ClaimDocumentTypeResource.class);

    private static final String ENTITY_NAME = "claimDocumentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimDocumentTypeService claimDocumentTypeService;

    public ClaimDocumentTypeResource(ClaimDocumentTypeService claimDocumentTypeService) {
        this.claimDocumentTypeService = claimDocumentTypeService;
    }

    /**
     * {@code POST  /claim-document-types} : Create a new claimDocumentType.
     *
     * @param claimDocumentTypeDTO the claimDocumentTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimDocumentTypeDTO, or with status {@code 400 (Bad Request)} if the claimDocumentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-document-types")
    public ResponseEntity<ClaimDocumentTypeDTO> createClaimDocumentType(@RequestBody ClaimDocumentTypeDTO claimDocumentTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimDocumentType : {}", claimDocumentTypeDTO);
        if (claimDocumentTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimDocumentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimDocumentTypeDTO result = claimDocumentTypeService.save(claimDocumentTypeDTO);
        return ResponseEntity.created(new URI("/api/claim-document-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-document-types} : Updates an existing claimDocumentType.
     *
     * @param claimDocumentTypeDTO the claimDocumentTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimDocumentTypeDTO,
     * or with status {@code 400 (Bad Request)} if the claimDocumentTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimDocumentTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-document-types")
    public ResponseEntity<ClaimDocumentTypeDTO> updateClaimDocumentType(@RequestBody ClaimDocumentTypeDTO claimDocumentTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimDocumentType : {}", claimDocumentTypeDTO);
        if (claimDocumentTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimDocumentTypeDTO result = claimDocumentTypeService.save(claimDocumentTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimDocumentTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-document-types} : get all the claimDocumentTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimDocumentTypes in body.
     */
    @GetMapping("/claim-document-types")
    public List<ClaimDocumentTypeDTO> getAllClaimDocumentTypes() {
        log.debug("REST request to get all ClaimDocumentTypes");
        return claimDocumentTypeService.findAll();
    }

    /**
     * {@code GET  /claim-document-types/:id} : get the "id" claimDocumentType.
     *
     * @param id the id of the claimDocumentTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimDocumentTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-document-types/{id}")
    public ResponseEntity<ClaimDocumentTypeDTO> getClaimDocumentType(@PathVariable Long id) {
        log.debug("REST request to get ClaimDocumentType : {}", id);
        Optional<ClaimDocumentTypeDTO> claimDocumentTypeDTO = claimDocumentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimDocumentTypeDTO);
    }

    /**
     * {@code DELETE  /claim-document-types/:id} : delete the "id" claimDocumentType.
     *
     * @param id the id of the claimDocumentTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-document-types/{id}")
    public ResponseEntity<Void> deleteClaimDocumentType(@PathVariable Long id) {
        log.debug("REST request to delete ClaimDocumentType : {}", id);
        claimDocumentTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
