package com.eshipper.web.rest;

import com.eshipper.service.ContactPreferenceService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ContactPreferenceDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ContactPreference}.
 */
@RestController
@RequestMapping("/api")
public class ContactPreferenceResource {

    private final Logger log = LoggerFactory.getLogger(ContactPreferenceResource.class);

    private static final String ENTITY_NAME = "contactPreference";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactPreferenceService contactPreferenceService;

    public ContactPreferenceResource(ContactPreferenceService contactPreferenceService) {
        this.contactPreferenceService = contactPreferenceService;
    }

    /**
     * {@code POST  /contact-preferences} : Create a new contactPreference.
     *
     * @param contactPreferenceDTO the contactPreferenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactPreferenceDTO, or with status {@code 400 (Bad Request)} if the contactPreference has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-preferences")
    public ResponseEntity<ContactPreferenceDTO> createContactPreference(@RequestBody ContactPreferenceDTO contactPreferenceDTO) throws URISyntaxException {
        log.debug("REST request to save ContactPreference : {}", contactPreferenceDTO);
        if (contactPreferenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactPreference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactPreferenceDTO result = contactPreferenceService.save(contactPreferenceDTO);
        return ResponseEntity.created(new URI("/api/contact-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-preferences} : Updates an existing contactPreference.
     *
     * @param contactPreferenceDTO the contactPreferenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactPreferenceDTO,
     * or with status {@code 400 (Bad Request)} if the contactPreferenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactPreferenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-preferences")
    public ResponseEntity<ContactPreferenceDTO> updateContactPreference(@RequestBody ContactPreferenceDTO contactPreferenceDTO) throws URISyntaxException {
        log.debug("REST request to update ContactPreference : {}", contactPreferenceDTO);
        if (contactPreferenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactPreferenceDTO result = contactPreferenceService.save(contactPreferenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactPreferenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-preferences} : get all the contactPreferences.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactPreferences in body.
     */
    @GetMapping("/contact-preferences")
    public List<ContactPreferenceDTO> getAllContactPreferences() {
        log.debug("REST request to get all ContactPreferences");
        return contactPreferenceService.findAll();
    }

    /**
     * {@code GET  /contact-preferences/:id} : get the "id" contactPreference.
     *
     * @param id the id of the contactPreferenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactPreferenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-preferences/{id}")
    public ResponseEntity<ContactPreferenceDTO> getContactPreference(@PathVariable Long id) {
        log.debug("REST request to get ContactPreference : {}", id);
        Optional<ContactPreferenceDTO> contactPreferenceDTO = contactPreferenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactPreferenceDTO);
    }

    /**
     * {@code DELETE  /contact-preferences/:id} : delete the "id" contactPreference.
     *
     * @param id the id of the contactPreferenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-preferences/{id}")
    public ResponseEntity<Void> deleteContactPreference(@PathVariable Long id) {
        log.debug("REST request to delete ContactPreference : {}", id);
        contactPreferenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
