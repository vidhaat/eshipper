package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimDocumentType;
import com.eshipper.repository.ClaimDocumentTypeRepository;
import com.eshipper.service.ClaimDocumentTypeService;
import com.eshipper.service.dto.ClaimDocumentTypeDTO;
import com.eshipper.service.mapper.ClaimDocumentTypeMapper;
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
 * Integration tests for the {@link ClaimDocumentTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimDocumentTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimDocumentTypeRepository claimDocumentTypeRepository;

    @Autowired
    private ClaimDocumentTypeMapper claimDocumentTypeMapper;

    @Autowired
    private ClaimDocumentTypeService claimDocumentTypeService;

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

    private MockMvc restClaimDocumentTypeMockMvc;

    private ClaimDocumentType claimDocumentType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimDocumentTypeResource claimDocumentTypeResource = new ClaimDocumentTypeResource(claimDocumentTypeService);
        this.restClaimDocumentTypeMockMvc = MockMvcBuilders.standaloneSetup(claimDocumentTypeResource)
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
    public static ClaimDocumentType createEntity(EntityManager em) {
        ClaimDocumentType claimDocumentType = new ClaimDocumentType()
            .name(DEFAULT_NAME);
        return claimDocumentType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimDocumentType createUpdatedEntity(EntityManager em) {
        ClaimDocumentType claimDocumentType = new ClaimDocumentType()
            .name(UPDATED_NAME);
        return claimDocumentType;
    }

    @BeforeEach
    public void initTest() {
        claimDocumentType = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimDocumentType() throws Exception {
        int databaseSizeBeforeCreate = claimDocumentTypeRepository.findAll().size();

        // Create the ClaimDocumentType
        ClaimDocumentTypeDTO claimDocumentTypeDTO = claimDocumentTypeMapper.toDto(claimDocumentType);
        restClaimDocumentTypeMockMvc.perform(post("/api/claim-document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimDocumentTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimDocumentType in the database
        List<ClaimDocumentType> claimDocumentTypeList = claimDocumentTypeRepository.findAll();
        assertThat(claimDocumentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimDocumentType testClaimDocumentType = claimDocumentTypeList.get(claimDocumentTypeList.size() - 1);
        assertThat(testClaimDocumentType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createClaimDocumentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimDocumentTypeRepository.findAll().size();

        // Create the ClaimDocumentType with an existing ID
        claimDocumentType.setId(1L);
        ClaimDocumentTypeDTO claimDocumentTypeDTO = claimDocumentTypeMapper.toDto(claimDocumentType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimDocumentTypeMockMvc.perform(post("/api/claim-document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimDocumentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimDocumentType in the database
        List<ClaimDocumentType> claimDocumentTypeList = claimDocumentTypeRepository.findAll();
        assertThat(claimDocumentTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimDocumentTypes() throws Exception {
        // Initialize the database
        claimDocumentTypeRepository.saveAndFlush(claimDocumentType);

        // Get all the claimDocumentTypeList
        restClaimDocumentTypeMockMvc.perform(get("/api/claim-document-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimDocumentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getClaimDocumentType() throws Exception {
        // Initialize the database
        claimDocumentTypeRepository.saveAndFlush(claimDocumentType);

        // Get the claimDocumentType
        restClaimDocumentTypeMockMvc.perform(get("/api/claim-document-types/{id}", claimDocumentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimDocumentType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingClaimDocumentType() throws Exception {
        // Get the claimDocumentType
        restClaimDocumentTypeMockMvc.perform(get("/api/claim-document-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimDocumentType() throws Exception {
        // Initialize the database
        claimDocumentTypeRepository.saveAndFlush(claimDocumentType);

        int databaseSizeBeforeUpdate = claimDocumentTypeRepository.findAll().size();

        // Update the claimDocumentType
        ClaimDocumentType updatedClaimDocumentType = claimDocumentTypeRepository.findById(claimDocumentType.getId()).get();
        // Disconnect from session so that the updates on updatedClaimDocumentType are not directly saved in db
        em.detach(updatedClaimDocumentType);
        updatedClaimDocumentType
            .name(UPDATED_NAME);
        ClaimDocumentTypeDTO claimDocumentTypeDTO = claimDocumentTypeMapper.toDto(updatedClaimDocumentType);

        restClaimDocumentTypeMockMvc.perform(put("/api/claim-document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimDocumentTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimDocumentType in the database
        List<ClaimDocumentType> claimDocumentTypeList = claimDocumentTypeRepository.findAll();
        assertThat(claimDocumentTypeList).hasSize(databaseSizeBeforeUpdate);
        ClaimDocumentType testClaimDocumentType = claimDocumentTypeList.get(claimDocumentTypeList.size() - 1);
        assertThat(testClaimDocumentType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimDocumentType() throws Exception {
        int databaseSizeBeforeUpdate = claimDocumentTypeRepository.findAll().size();

        // Create the ClaimDocumentType
        ClaimDocumentTypeDTO claimDocumentTypeDTO = claimDocumentTypeMapper.toDto(claimDocumentType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimDocumentTypeMockMvc.perform(put("/api/claim-document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimDocumentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimDocumentType in the database
        List<ClaimDocumentType> claimDocumentTypeList = claimDocumentTypeRepository.findAll();
        assertThat(claimDocumentTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimDocumentType() throws Exception {
        // Initialize the database
        claimDocumentTypeRepository.saveAndFlush(claimDocumentType);

        int databaseSizeBeforeDelete = claimDocumentTypeRepository.findAll().size();

        // Delete the claimDocumentType
        restClaimDocumentTypeMockMvc.perform(delete("/api/claim-document-types/{id}", claimDocumentType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimDocumentType> claimDocumentTypeList = claimDocumentTypeRepository.findAll();
        assertThat(claimDocumentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
