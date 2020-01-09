package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimMissingDocument;
import com.eshipper.repository.ClaimMissingDocumentRepository;
import com.eshipper.service.ClaimMissingDocumentService;
import com.eshipper.service.dto.ClaimMissingDocumentDTO;
import com.eshipper.service.mapper.ClaimMissingDocumentMapper;
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
 * Integration tests for the {@link ClaimMissingDocumentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimMissingDocumentResourceIT {

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFY_CLIENT = false;
    private static final Boolean UPDATED_NOTIFY_CLIENT = true;

    private static final Boolean DEFAULT_UPLOADED = false;
    private static final Boolean UPDATED_UPLOADED = true;

    @Autowired
    private ClaimMissingDocumentRepository claimMissingDocumentRepository;

    @Autowired
    private ClaimMissingDocumentMapper claimMissingDocumentMapper;

    @Autowired
    private ClaimMissingDocumentService claimMissingDocumentService;

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

    private MockMvc restClaimMissingDocumentMockMvc;

    private ClaimMissingDocument claimMissingDocument;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimMissingDocumentResource claimMissingDocumentResource = new ClaimMissingDocumentResource(claimMissingDocumentService);
        this.restClaimMissingDocumentMockMvc = MockMvcBuilders.standaloneSetup(claimMissingDocumentResource)
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
    public static ClaimMissingDocument createEntity(EntityManager em) {
        ClaimMissingDocument claimMissingDocument = new ClaimMissingDocument()
            .documentName(DEFAULT_DOCUMENT_NAME)
            .notifyClient(DEFAULT_NOTIFY_CLIENT)
            .uploaded(DEFAULT_UPLOADED);
        return claimMissingDocument;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimMissingDocument createUpdatedEntity(EntityManager em) {
        ClaimMissingDocument claimMissingDocument = new ClaimMissingDocument()
            .documentName(UPDATED_DOCUMENT_NAME)
            .notifyClient(UPDATED_NOTIFY_CLIENT)
            .uploaded(UPDATED_UPLOADED);
        return claimMissingDocument;
    }

    @BeforeEach
    public void initTest() {
        claimMissingDocument = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimMissingDocument() throws Exception {
        int databaseSizeBeforeCreate = claimMissingDocumentRepository.findAll().size();

        // Create the ClaimMissingDocument
        ClaimMissingDocumentDTO claimMissingDocumentDTO = claimMissingDocumentMapper.toDto(claimMissingDocument);
        restClaimMissingDocumentMockMvc.perform(post("/api/claim-missing-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimMissingDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimMissingDocument in the database
        List<ClaimMissingDocument> claimMissingDocumentList = claimMissingDocumentRepository.findAll();
        assertThat(claimMissingDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimMissingDocument testClaimMissingDocument = claimMissingDocumentList.get(claimMissingDocumentList.size() - 1);
        assertThat(testClaimMissingDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testClaimMissingDocument.isNotifyClient()).isEqualTo(DEFAULT_NOTIFY_CLIENT);
        assertThat(testClaimMissingDocument.isUploaded()).isEqualTo(DEFAULT_UPLOADED);
    }

    @Test
    @Transactional
    public void createClaimMissingDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimMissingDocumentRepository.findAll().size();

        // Create the ClaimMissingDocument with an existing ID
        claimMissingDocument.setId(1L);
        ClaimMissingDocumentDTO claimMissingDocumentDTO = claimMissingDocumentMapper.toDto(claimMissingDocument);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimMissingDocumentMockMvc.perform(post("/api/claim-missing-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimMissingDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimMissingDocument in the database
        List<ClaimMissingDocument> claimMissingDocumentList = claimMissingDocumentRepository.findAll();
        assertThat(claimMissingDocumentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimMissingDocuments() throws Exception {
        // Initialize the database
        claimMissingDocumentRepository.saveAndFlush(claimMissingDocument);

        // Get all the claimMissingDocumentList
        restClaimMissingDocumentMockMvc.perform(get("/api/claim-missing-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimMissingDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].notifyClient").value(hasItem(DEFAULT_NOTIFY_CLIENT.booleanValue())))
            .andExpect(jsonPath("$.[*].uploaded").value(hasItem(DEFAULT_UPLOADED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getClaimMissingDocument() throws Exception {
        // Initialize the database
        claimMissingDocumentRepository.saveAndFlush(claimMissingDocument);

        // Get the claimMissingDocument
        restClaimMissingDocumentMockMvc.perform(get("/api/claim-missing-documents/{id}", claimMissingDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimMissingDocument.getId().intValue()))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.notifyClient").value(DEFAULT_NOTIFY_CLIENT.booleanValue()))
            .andExpect(jsonPath("$.uploaded").value(DEFAULT_UPLOADED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingClaimMissingDocument() throws Exception {
        // Get the claimMissingDocument
        restClaimMissingDocumentMockMvc.perform(get("/api/claim-missing-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimMissingDocument() throws Exception {
        // Initialize the database
        claimMissingDocumentRepository.saveAndFlush(claimMissingDocument);

        int databaseSizeBeforeUpdate = claimMissingDocumentRepository.findAll().size();

        // Update the claimMissingDocument
        ClaimMissingDocument updatedClaimMissingDocument = claimMissingDocumentRepository.findById(claimMissingDocument.getId()).get();
        // Disconnect from session so that the updates on updatedClaimMissingDocument are not directly saved in db
        em.detach(updatedClaimMissingDocument);
        updatedClaimMissingDocument
            .documentName(UPDATED_DOCUMENT_NAME)
            .notifyClient(UPDATED_NOTIFY_CLIENT)
            .uploaded(UPDATED_UPLOADED);
        ClaimMissingDocumentDTO claimMissingDocumentDTO = claimMissingDocumentMapper.toDto(updatedClaimMissingDocument);

        restClaimMissingDocumentMockMvc.perform(put("/api/claim-missing-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimMissingDocumentDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimMissingDocument in the database
        List<ClaimMissingDocument> claimMissingDocumentList = claimMissingDocumentRepository.findAll();
        assertThat(claimMissingDocumentList).hasSize(databaseSizeBeforeUpdate);
        ClaimMissingDocument testClaimMissingDocument = claimMissingDocumentList.get(claimMissingDocumentList.size() - 1);
        assertThat(testClaimMissingDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testClaimMissingDocument.isNotifyClient()).isEqualTo(UPDATED_NOTIFY_CLIENT);
        assertThat(testClaimMissingDocument.isUploaded()).isEqualTo(UPDATED_UPLOADED);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimMissingDocument() throws Exception {
        int databaseSizeBeforeUpdate = claimMissingDocumentRepository.findAll().size();

        // Create the ClaimMissingDocument
        ClaimMissingDocumentDTO claimMissingDocumentDTO = claimMissingDocumentMapper.toDto(claimMissingDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimMissingDocumentMockMvc.perform(put("/api/claim-missing-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimMissingDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimMissingDocument in the database
        List<ClaimMissingDocument> claimMissingDocumentList = claimMissingDocumentRepository.findAll();
        assertThat(claimMissingDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimMissingDocument() throws Exception {
        // Initialize the database
        claimMissingDocumentRepository.saveAndFlush(claimMissingDocument);

        int databaseSizeBeforeDelete = claimMissingDocumentRepository.findAll().size();

        // Delete the claimMissingDocument
        restClaimMissingDocumentMockMvc.perform(delete("/api/claim-missing-documents/{id}", claimMissingDocument.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimMissingDocument> claimMissingDocumentList = claimMissingDocumentRepository.findAll();
        assertThat(claimMissingDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
