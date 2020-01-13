package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimStatus;
import com.eshipper.repository.ClaimStatusRepository;
import com.eshipper.service.ClaimStatusService;
import com.eshipper.service.dto.ClaimStatusDTO;
import com.eshipper.service.mapper.ClaimStatusMapper;
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
 * Integration tests for the {@link ClaimStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimStatusRepository claimStatusRepository;

    @Autowired
    private ClaimStatusMapper claimStatusMapper;

    @Autowired
    private ClaimStatusService claimStatusService;

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

    private MockMvc restClaimStatusMockMvc;

    private ClaimStatus claimStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimStatusResource claimStatusResource = new ClaimStatusResource(claimStatusService);
        this.restClaimStatusMockMvc = MockMvcBuilders.standaloneSetup(claimStatusResource)
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
    public static ClaimStatus createEntity(EntityManager em) {
        ClaimStatus claimStatus = new ClaimStatus()
            .name(DEFAULT_NAME);
        return claimStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimStatus createUpdatedEntity(EntityManager em) {
        ClaimStatus claimStatus = new ClaimStatus()
            .name(UPDATED_NAME);
        return claimStatus;
    }

    @BeforeEach
    public void initTest() {
        claimStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimStatus() throws Exception {
        int databaseSizeBeforeCreate = claimStatusRepository.findAll().size();

        // Create the ClaimStatus
        ClaimStatusDTO claimStatusDTO = claimStatusMapper.toDto(claimStatus);
        restClaimStatusMockMvc.perform(post("/api/claim-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimStatus in the database
        List<ClaimStatus> claimStatusList = claimStatusRepository.findAll();
        assertThat(claimStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimStatus testClaimStatus = claimStatusList.get(claimStatusList.size() - 1);
        assertThat(testClaimStatus.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createClaimStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimStatusRepository.findAll().size();

        // Create the ClaimStatus with an existing ID
        claimStatus.setId(1L);
        ClaimStatusDTO claimStatusDTO = claimStatusMapper.toDto(claimStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimStatusMockMvc.perform(post("/api/claim-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimStatus in the database
        List<ClaimStatus> claimStatusList = claimStatusRepository.findAll();
        assertThat(claimStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimStatuses() throws Exception {
        // Initialize the database
        claimStatusRepository.saveAndFlush(claimStatus);

        // Get all the claimStatusList
        restClaimStatusMockMvc.perform(get("/api/claim-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getClaimStatus() throws Exception {
        // Initialize the database
        claimStatusRepository.saveAndFlush(claimStatus);

        // Get the claimStatus
        restClaimStatusMockMvc.perform(get("/api/claim-statuses/{id}", claimStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingClaimStatus() throws Exception {
        // Get the claimStatus
        restClaimStatusMockMvc.perform(get("/api/claim-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimStatus() throws Exception {
        // Initialize the database
        claimStatusRepository.saveAndFlush(claimStatus);

        int databaseSizeBeforeUpdate = claimStatusRepository.findAll().size();

        // Update the claimStatus
        ClaimStatus updatedClaimStatus = claimStatusRepository.findById(claimStatus.getId()).get();
        // Disconnect from session so that the updates on updatedClaimStatus are not directly saved in db
        em.detach(updatedClaimStatus);
        updatedClaimStatus
            .name(UPDATED_NAME);
        ClaimStatusDTO claimStatusDTO = claimStatusMapper.toDto(updatedClaimStatus);

        restClaimStatusMockMvc.perform(put("/api/claim-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimStatus in the database
        List<ClaimStatus> claimStatusList = claimStatusRepository.findAll();
        assertThat(claimStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimStatus testClaimStatus = claimStatusList.get(claimStatusList.size() - 1);
        assertThat(testClaimStatus.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimStatusRepository.findAll().size();

        // Create the ClaimStatus
        ClaimStatusDTO claimStatusDTO = claimStatusMapper.toDto(claimStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimStatusMockMvc.perform(put("/api/claim-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimStatus in the database
        List<ClaimStatus> claimStatusList = claimStatusRepository.findAll();
        assertThat(claimStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimStatus() throws Exception {
        // Initialize the database
        claimStatusRepository.saveAndFlush(claimStatus);

        int databaseSizeBeforeDelete = claimStatusRepository.findAll().size();

        // Delete the claimStatus
        restClaimStatusMockMvc.perform(delete("/api/claim-statuses/{id}", claimStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimStatus> claimStatusList = claimStatusRepository.findAll();
        assertThat(claimStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
