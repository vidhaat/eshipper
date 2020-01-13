package com.eshipper.web.rest;

import com.eshipper.service.ElasticsearchStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticsearchStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ElasticsearchStatus}.
 */
@RestController
@RequestMapping("/api")
public class ElasticsearchStatusResource {

    private final Logger log = LoggerFactory.getLogger(ElasticsearchStatusResource.class);

    private static final String ENTITY_NAME = "elasticsearchStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticsearchStatusService elasticsearchStatusService;

    public ElasticsearchStatusResource(ElasticsearchStatusService elasticsearchStatusService) {
        this.elasticsearchStatusService = elasticsearchStatusService;
    }

    /**
     * {@code POST  /elasticsearch-statuses} : Create a new elasticsearchStatus.
     *
     * @param elasticsearchStatusDTO the elasticsearchStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticsearchStatusDTO, or with status {@code 400 (Bad Request)} if the elasticsearchStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elasticsearch-statuses")
    public ResponseEntity<ElasticsearchStatusDTO> createElasticsearchStatus(@RequestBody ElasticsearchStatusDTO elasticsearchStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticsearchStatus : {}", elasticsearchStatusDTO);
        if (elasticsearchStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticsearchStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticsearchStatusDTO result = elasticsearchStatusService.save(elasticsearchStatusDTO);
        return ResponseEntity.created(new URI("/api/elasticsearch-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elasticsearch-statuses} : Updates an existing elasticsearchStatus.
     *
     * @param elasticsearchStatusDTO the elasticsearchStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticsearchStatusDTO,
     * or with status {@code 400 (Bad Request)} if the elasticsearchStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticsearchStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elasticsearch-statuses")
    public ResponseEntity<ElasticsearchStatusDTO> updateElasticsearchStatus(@RequestBody ElasticsearchStatusDTO elasticsearchStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticsearchStatus : {}", elasticsearchStatusDTO);
        if (elasticsearchStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticsearchStatusDTO result = elasticsearchStatusService.save(elasticsearchStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticsearchStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elasticsearch-statuses} : get all the elasticsearchStatuses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticsearchStatuses in body.
     */
    @GetMapping("/elasticsearch-statuses")
    public List<ElasticsearchStatusDTO> getAllElasticsearchStatuses() {
        log.debug("REST request to get all ElasticsearchStatuses");
        return elasticsearchStatusService.findAll();
    }

    /**
     * {@code GET  /elasticsearch-statuses/:id} : get the "id" elasticsearchStatus.
     *
     * @param id the id of the elasticsearchStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticsearchStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elasticsearch-statuses/{id}")
    public ResponseEntity<ElasticsearchStatusDTO> getElasticsearchStatus(@PathVariable Long id) {
        log.debug("REST request to get ElasticsearchStatus : {}", id);
        Optional<ElasticsearchStatusDTO> elasticsearchStatusDTO = elasticsearchStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticsearchStatusDTO);
    }

    /**
     * {@code DELETE  /elasticsearch-statuses/:id} : delete the "id" elasticsearchStatus.
     *
     * @param id the id of the elasticsearchStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elasticsearch-statuses/{id}")
    public ResponseEntity<Void> deleteElasticsearchStatus(@PathVariable Long id) {
        log.debug("REST request to delete ElasticsearchStatus : {}", id);
        elasticsearchStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
