package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimSolution;
import com.eshipper.repository.ClaimSolutionRepository;
import com.eshipper.service.ClaimSolutionService;
import com.eshipper.service.dto.ClaimSolutionDTO;
import com.eshipper.service.mapper.ClaimSolutionMapper;
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
 * Integration tests for the {@link ClaimSolutionResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimSolutionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimSolutionRepository claimSolutionRepository;

    @Autowired
    private ClaimSolutionMapper claimSolutionMapper;

    @Autowired
    private ClaimSolutionService claimSolutionService;

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

    private MockMvc restClaimSolutionMockMvc;

    private ClaimSolution claimSolution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimSolutionResource claimSolutionResource = new ClaimSolutionResource(claimSolutionService);
        this.restClaimSolutionMockMvc = MockMvcBuilders.standaloneSetup(claimSolutionResource)
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
    public static ClaimSolution createEntity(EntityManager em) {
        ClaimSolution claimSolution = new ClaimSolution()
            .name(DEFAULT_NAME);
        return claimSolution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimSolution createUpdatedEntity(EntityManager em) {
        ClaimSolution claimSolution = new ClaimSolution()
            .name(UPDATED_NAME);
        return claimSolution;
    }

    @BeforeEach
    public void initTest() {
        claimSolution = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimSolution() throws Exception {
        int databaseSizeBeforeCreate = claimSolutionRepository.findAll().size();

        // Create the ClaimSolution
        ClaimSolutionDTO claimSolutionDTO = claimSolutionMapper.toDto(claimSolution);
        restClaimSolutionMockMvc.perform(post("/api/claim-solutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSolutionDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimSolution in the database
        List<ClaimSolution> claimSolutionList = claimSolutionRepository.findAll();
        assertThat(claimSolutionList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimSolution testClaimSolution = claimSolutionList.get(claimSolutionList.size() - 1);
        assertThat(testClaimSolution.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createClaimSolutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimSolutionRepository.findAll().size();

        // Create the ClaimSolution with an existing ID
        claimSolution.setId(1L);
        ClaimSolutionDTO claimSolutionDTO = claimSolutionMapper.toDto(claimSolution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimSolutionMockMvc.perform(post("/api/claim-solutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSolutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimSolution in the database
        List<ClaimSolution> claimSolutionList = claimSolutionRepository.findAll();
        assertThat(claimSolutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimSolutions() throws Exception {
        // Initialize the database
        claimSolutionRepository.saveAndFlush(claimSolution);

        // Get all the claimSolutionList
        restClaimSolutionMockMvc.perform(get("/api/claim-solutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimSolution.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getClaimSolution() throws Exception {
        // Initialize the database
        claimSolutionRepository.saveAndFlush(claimSolution);

        // Get the claimSolution
        restClaimSolutionMockMvc.perform(get("/api/claim-solutions/{id}", claimSolution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimSolution.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingClaimSolution() throws Exception {
        // Get the claimSolution
        restClaimSolutionMockMvc.perform(get("/api/claim-solutions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimSolution() throws Exception {
        // Initialize the database
        claimSolutionRepository.saveAndFlush(claimSolution);

        int databaseSizeBeforeUpdate = claimSolutionRepository.findAll().size();

        // Update the claimSolution
        ClaimSolution updatedClaimSolution = claimSolutionRepository.findById(claimSolution.getId()).get();
        // Disconnect from session so that the updates on updatedClaimSolution are not directly saved in db
        em.detach(updatedClaimSolution);
        updatedClaimSolution
            .name(UPDATED_NAME);
        ClaimSolutionDTO claimSolutionDTO = claimSolutionMapper.toDto(updatedClaimSolution);

        restClaimSolutionMockMvc.perform(put("/api/claim-solutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSolutionDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimSolution in the database
        List<ClaimSolution> claimSolutionList = claimSolutionRepository.findAll();
        assertThat(claimSolutionList).hasSize(databaseSizeBeforeUpdate);
        ClaimSolution testClaimSolution = claimSolutionList.get(claimSolutionList.size() - 1);
        assertThat(testClaimSolution.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimSolution() throws Exception {
        int databaseSizeBeforeUpdate = claimSolutionRepository.findAll().size();

        // Create the ClaimSolution
        ClaimSolutionDTO claimSolutionDTO = claimSolutionMapper.toDto(claimSolution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimSolutionMockMvc.perform(put("/api/claim-solutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSolutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimSolution in the database
        List<ClaimSolution> claimSolutionList = claimSolutionRepository.findAll();
        assertThat(claimSolutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimSolution() throws Exception {
        // Initialize the database
        claimSolutionRepository.saveAndFlush(claimSolution);

        int databaseSizeBeforeDelete = claimSolutionRepository.findAll().size();

        // Delete the claimSolution
        restClaimSolutionMockMvc.perform(delete("/api/claim-solutions/{id}", claimSolution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimSolution> claimSolutionList = claimSolutionRepository.findAll();
        assertThat(claimSolutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
