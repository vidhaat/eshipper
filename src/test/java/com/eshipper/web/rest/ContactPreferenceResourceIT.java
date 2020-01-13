package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ContactPreference;
import com.eshipper.repository.ContactPreferenceRepository;
import com.eshipper.service.ContactPreferenceService;
import com.eshipper.service.dto.ContactPreferenceDTO;
import com.eshipper.service.mapper.ContactPreferenceMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ContactPreferenceResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ContactPreferenceResourceIT {

    @Autowired
    private ContactPreferenceRepository contactPreferenceRepository;

    @Autowired
    private ContactPreferenceMapper contactPreferenceMapper;

    @Autowired
    private ContactPreferenceService contactPreferenceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restContactPreferenceMockMvc;

    private ContactPreference contactPreference;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactPreferenceResource contactPreferenceResource = new ContactPreferenceResource(contactPreferenceService);
        this.restContactPreferenceMockMvc = MockMvcBuilders.standaloneSetup(contactPreferenceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactPreference createEntity(EntityManager em) {
        ContactPreference contactPreference = new ContactPreference();
        return contactPreference;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactPreference createUpdatedEntity(EntityManager em) {
        ContactPreference contactPreference = new ContactPreference();
        return contactPreference;
    }

    @BeforeEach
    public void initTest() {
        contactPreference = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactPreference() throws Exception {
        int databaseSizeBeforeCreate = contactPreferenceRepository.findAll().size();

        // Create the ContactPreference
        ContactPreferenceDTO contactPreferenceDTO = contactPreferenceMapper.toDto(contactPreference);
        restContactPreferenceMockMvc.perform(post("/api/contact-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactPreferenceDTO)))
            .andExpect(status().isCreated());

        // Validate the ContactPreference in the database
        List<ContactPreference> contactPreferenceList = contactPreferenceRepository.findAll();
        assertThat(contactPreferenceList).hasSize(databaseSizeBeforeCreate + 1);
        ContactPreference testContactPreference = contactPreferenceList.get(contactPreferenceList.size() - 1);
    }

    @Test
    @Transactional
    public void createContactPreferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactPreferenceRepository.findAll().size();

        // Create the ContactPreference with an existing ID
        contactPreference.setId(1L);
        ContactPreferenceDTO contactPreferenceDTO = contactPreferenceMapper.toDto(contactPreference);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactPreferenceMockMvc.perform(post("/api/contact-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactPreferenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactPreference in the database
        List<ContactPreference> contactPreferenceList = contactPreferenceRepository.findAll();
        assertThat(contactPreferenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContactPreferences() throws Exception {
        // Initialize the database
        contactPreferenceRepository.saveAndFlush(contactPreference);

        // Get all the contactPreferenceList
        restContactPreferenceMockMvc.perform(get("/api/contact-preferences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactPreference.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getContactPreference() throws Exception {
        // Initialize the database
        contactPreferenceRepository.saveAndFlush(contactPreference);

        // Get the contactPreference
        restContactPreferenceMockMvc.perform(get("/api/contact-preferences/{id}", contactPreference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contactPreference.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingContactPreference() throws Exception {
        // Get the contactPreference
        restContactPreferenceMockMvc.perform(get("/api/contact-preferences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactPreference() throws Exception {
        // Initialize the database
        contactPreferenceRepository.saveAndFlush(contactPreference);

        int databaseSizeBeforeUpdate = contactPreferenceRepository.findAll().size();

        // Update the contactPreference
        ContactPreference updatedContactPreference = contactPreferenceRepository.findById(contactPreference.getId()).get();
        // Disconnect from session so that the updates on updatedContactPreference are not directly saved in db
        em.detach(updatedContactPreference);
        ContactPreferenceDTO contactPreferenceDTO = contactPreferenceMapper.toDto(updatedContactPreference);

        restContactPreferenceMockMvc.perform(put("/api/contact-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactPreferenceDTO)))
            .andExpect(status().isOk());

        // Validate the ContactPreference in the database
        List<ContactPreference> contactPreferenceList = contactPreferenceRepository.findAll();
        assertThat(contactPreferenceList).hasSize(databaseSizeBeforeUpdate);
        ContactPreference testContactPreference = contactPreferenceList.get(contactPreferenceList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingContactPreference() throws Exception {
        int databaseSizeBeforeUpdate = contactPreferenceRepository.findAll().size();

        // Create the ContactPreference
        ContactPreferenceDTO contactPreferenceDTO = contactPreferenceMapper.toDto(contactPreference);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactPreferenceMockMvc.perform(put("/api/contact-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactPreferenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactPreference in the database
        List<ContactPreference> contactPreferenceList = contactPreferenceRepository.findAll();
        assertThat(contactPreferenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContactPreference() throws Exception {
        // Initialize the database
        contactPreferenceRepository.saveAndFlush(contactPreference);

        int databaseSizeBeforeDelete = contactPreferenceRepository.findAll().size();

        // Delete the contactPreference
        restContactPreferenceMockMvc.perform(delete("/api/contact-preferences/{id}", contactPreference.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactPreference> contactPreferenceList = contactPreferenceRepository.findAll();
        assertThat(contactPreferenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
