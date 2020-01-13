package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticShippingClaim;
import com.eshipper.repository.ElasticShippingClaimRepository;
import com.eshipper.service.ElasticShippingClaimService;
import com.eshipper.service.dto.ElasticShippingClaimDTO;
import com.eshipper.service.mapper.ElasticShippingClaimMapper;
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
 * Integration tests for the {@link ElasticShippingClaimResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ElasticShippingClaimResourceIT {

    @Autowired
    private ElasticShippingClaimRepository elasticShippingClaimRepository;

    @Autowired
    private ElasticShippingClaimMapper elasticShippingClaimMapper;

    @Autowired
    private ElasticShippingClaimService elasticShippingClaimService;

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

    private MockMvc restElasticShippingClaimMockMvc;

    private ElasticShippingClaim elasticShippingClaim;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElasticShippingClaimResource elasticShippingClaimResource = new ElasticShippingClaimResource(elasticShippingClaimService);
        this.restElasticShippingClaimMockMvc = MockMvcBuilders.standaloneSetup(elasticShippingClaimResource)
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
    public static ElasticShippingClaim createEntity(EntityManager em) {
        ElasticShippingClaim elasticShippingClaim = new ElasticShippingClaim();
        return elasticShippingClaim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticShippingClaim createUpdatedEntity(EntityManager em) {
        ElasticShippingClaim elasticShippingClaim = new ElasticShippingClaim();
        return elasticShippingClaim;
    }

    @BeforeEach
    public void initTest() {
        elasticShippingClaim = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticShippingClaim() throws Exception {
        int databaseSizeBeforeCreate = elasticShippingClaimRepository.findAll().size();

        // Create the ElasticShippingClaim
        ElasticShippingClaimDTO elasticShippingClaimDTO = elasticShippingClaimMapper.toDto(elasticShippingClaim);
        restElasticShippingClaimMockMvc.perform(post("/api/elastic-shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticShippingClaimDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticShippingClaim in the database
        List<ElasticShippingClaim> elasticShippingClaimList = elasticShippingClaimRepository.findAll();
        assertThat(elasticShippingClaimList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticShippingClaim testElasticShippingClaim = elasticShippingClaimList.get(elasticShippingClaimList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticShippingClaimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticShippingClaimRepository.findAll().size();

        // Create the ElasticShippingClaim with an existing ID
        elasticShippingClaim.setId(1L);
        ElasticShippingClaimDTO elasticShippingClaimDTO = elasticShippingClaimMapper.toDto(elasticShippingClaim);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticShippingClaimMockMvc.perform(post("/api/elastic-shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticShippingClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticShippingClaim in the database
        List<ElasticShippingClaim> elasticShippingClaimList = elasticShippingClaimRepository.findAll();
        assertThat(elasticShippingClaimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticShippingClaims() throws Exception {
        // Initialize the database
        elasticShippingClaimRepository.saveAndFlush(elasticShippingClaim);

        // Get all the elasticShippingClaimList
        restElasticShippingClaimMockMvc.perform(get("/api/elastic-shipping-claims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticShippingClaim.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticShippingClaim() throws Exception {
        // Initialize the database
        elasticShippingClaimRepository.saveAndFlush(elasticShippingClaim);

        // Get the elasticShippingClaim
        restElasticShippingClaimMockMvc.perform(get("/api/elastic-shipping-claims/{id}", elasticShippingClaim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elasticShippingClaim.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingElasticShippingClaim() throws Exception {
        // Get the elasticShippingClaim
        restElasticShippingClaimMockMvc.perform(get("/api/elastic-shipping-claims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticShippingClaim() throws Exception {
        // Initialize the database
        elasticShippingClaimRepository.saveAndFlush(elasticShippingClaim);

        int databaseSizeBeforeUpdate = elasticShippingClaimRepository.findAll().size();

        // Update the elasticShippingClaim
        ElasticShippingClaim updatedElasticShippingClaim = elasticShippingClaimRepository.findById(elasticShippingClaim.getId()).get();
        // Disconnect from session so that the updates on updatedElasticShippingClaim are not directly saved in db
        em.detach(updatedElasticShippingClaim);
        ElasticShippingClaimDTO elasticShippingClaimDTO = elasticShippingClaimMapper.toDto(updatedElasticShippingClaim);

        restElasticShippingClaimMockMvc.perform(put("/api/elastic-shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticShippingClaimDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticShippingClaim in the database
        List<ElasticShippingClaim> elasticShippingClaimList = elasticShippingClaimRepository.findAll();
        assertThat(elasticShippingClaimList).hasSize(databaseSizeBeforeUpdate);
        ElasticShippingClaim testElasticShippingClaim = elasticShippingClaimList.get(elasticShippingClaimList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticShippingClaim() throws Exception {
        int databaseSizeBeforeUpdate = elasticShippingClaimRepository.findAll().size();

        // Create the ElasticShippingClaim
        ElasticShippingClaimDTO elasticShippingClaimDTO = elasticShippingClaimMapper.toDto(elasticShippingClaim);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticShippingClaimMockMvc.perform(put("/api/elastic-shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticShippingClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticShippingClaim in the database
        List<ElasticShippingClaim> elasticShippingClaimList = elasticShippingClaimRepository.findAll();
        assertThat(elasticShippingClaimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticShippingClaim() throws Exception {
        // Initialize the database
        elasticShippingClaimRepository.saveAndFlush(elasticShippingClaim);

        int databaseSizeBeforeDelete = elasticShippingClaimRepository.findAll().size();

        // Delete the elasticShippingClaim
        restElasticShippingClaimMockMvc.perform(delete("/api/elastic-shipping-claims/{id}", elasticShippingClaim.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticShippingClaim> elasticShippingClaimList = elasticShippingClaimRepository.findAll();
        assertThat(elasticShippingClaimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
