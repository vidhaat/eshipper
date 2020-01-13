package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimCarrierRefundStatus;
import com.eshipper.repository.ClaimCarrierRefundStatusRepository;
import com.eshipper.service.ClaimCarrierRefundStatusService;
import com.eshipper.service.dto.ClaimCarrierRefundStatusDTO;
import com.eshipper.service.mapper.ClaimCarrierRefundStatusMapper;
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
 * Integration tests for the {@link ClaimCarrierRefundStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimCarrierRefundStatusResourceIT {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private ClaimCarrierRefundStatusRepository claimCarrierRefundStatusRepository;

    @Autowired
    private ClaimCarrierRefundStatusMapper claimCarrierRefundStatusMapper;

    @Autowired
    private ClaimCarrierRefundStatusService claimCarrierRefundStatusService;

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

    private MockMvc restClaimCarrierRefundStatusMockMvc;

    private ClaimCarrierRefundStatus claimCarrierRefundStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimCarrierRefundStatusResource claimCarrierRefundStatusResource = new ClaimCarrierRefundStatusResource(claimCarrierRefundStatusService);
        this.restClaimCarrierRefundStatusMockMvc = MockMvcBuilders.standaloneSetup(claimCarrierRefundStatusResource)
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
    public static ClaimCarrierRefundStatus createEntity(EntityManager em) {
        ClaimCarrierRefundStatus claimCarrierRefundStatus = new ClaimCarrierRefundStatus()
            .status(DEFAULT_STATUS);
        return claimCarrierRefundStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimCarrierRefundStatus createUpdatedEntity(EntityManager em) {
        ClaimCarrierRefundStatus claimCarrierRefundStatus = new ClaimCarrierRefundStatus()
            .status(UPDATED_STATUS);
        return claimCarrierRefundStatus;
    }

    @BeforeEach
    public void initTest() {
        claimCarrierRefundStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimCarrierRefundStatus() throws Exception {
        int databaseSizeBeforeCreate = claimCarrierRefundStatusRepository.findAll().size();

        // Create the ClaimCarrierRefundStatus
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO = claimCarrierRefundStatusMapper.toDto(claimCarrierRefundStatus);
        restClaimCarrierRefundStatusMockMvc.perform(post("/api/claim-carrier-refund-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimCarrierRefundStatus in the database
        List<ClaimCarrierRefundStatus> claimCarrierRefundStatusList = claimCarrierRefundStatusRepository.findAll();
        assertThat(claimCarrierRefundStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimCarrierRefundStatus testClaimCarrierRefundStatus = claimCarrierRefundStatusList.get(claimCarrierRefundStatusList.size() - 1);
        assertThat(testClaimCarrierRefundStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createClaimCarrierRefundStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimCarrierRefundStatusRepository.findAll().size();

        // Create the ClaimCarrierRefundStatus with an existing ID
        claimCarrierRefundStatus.setId(1L);
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO = claimCarrierRefundStatusMapper.toDto(claimCarrierRefundStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimCarrierRefundStatusMockMvc.perform(post("/api/claim-carrier-refund-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimCarrierRefundStatus in the database
        List<ClaimCarrierRefundStatus> claimCarrierRefundStatusList = claimCarrierRefundStatusRepository.findAll();
        assertThat(claimCarrierRefundStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimCarrierRefundStatuses() throws Exception {
        // Initialize the database
        claimCarrierRefundStatusRepository.saveAndFlush(claimCarrierRefundStatus);

        // Get all the claimCarrierRefundStatusList
        restClaimCarrierRefundStatusMockMvc.perform(get("/api/claim-carrier-refund-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimCarrierRefundStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getClaimCarrierRefundStatus() throws Exception {
        // Initialize the database
        claimCarrierRefundStatusRepository.saveAndFlush(claimCarrierRefundStatus);

        // Get the claimCarrierRefundStatus
        restClaimCarrierRefundStatusMockMvc.perform(get("/api/claim-carrier-refund-statuses/{id}", claimCarrierRefundStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimCarrierRefundStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingClaimCarrierRefundStatus() throws Exception {
        // Get the claimCarrierRefundStatus
        restClaimCarrierRefundStatusMockMvc.perform(get("/api/claim-carrier-refund-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimCarrierRefundStatus() throws Exception {
        // Initialize the database
        claimCarrierRefundStatusRepository.saveAndFlush(claimCarrierRefundStatus);

        int databaseSizeBeforeUpdate = claimCarrierRefundStatusRepository.findAll().size();

        // Update the claimCarrierRefundStatus
        ClaimCarrierRefundStatus updatedClaimCarrierRefundStatus = claimCarrierRefundStatusRepository.findById(claimCarrierRefundStatus.getId()).get();
        // Disconnect from session so that the updates on updatedClaimCarrierRefundStatus are not directly saved in db
        em.detach(updatedClaimCarrierRefundStatus);
        updatedClaimCarrierRefundStatus
            .status(UPDATED_STATUS);
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO = claimCarrierRefundStatusMapper.toDto(updatedClaimCarrierRefundStatus);

        restClaimCarrierRefundStatusMockMvc.perform(put("/api/claim-carrier-refund-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimCarrierRefundStatus in the database
        List<ClaimCarrierRefundStatus> claimCarrierRefundStatusList = claimCarrierRefundStatusRepository.findAll();
        assertThat(claimCarrierRefundStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimCarrierRefundStatus testClaimCarrierRefundStatus = claimCarrierRefundStatusList.get(claimCarrierRefundStatusList.size() - 1);
        assertThat(testClaimCarrierRefundStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimCarrierRefundStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimCarrierRefundStatusRepository.findAll().size();

        // Create the ClaimCarrierRefundStatus
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO = claimCarrierRefundStatusMapper.toDto(claimCarrierRefundStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimCarrierRefundStatusMockMvc.perform(put("/api/claim-carrier-refund-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimCarrierRefundStatus in the database
        List<ClaimCarrierRefundStatus> claimCarrierRefundStatusList = claimCarrierRefundStatusRepository.findAll();
        assertThat(claimCarrierRefundStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimCarrierRefundStatus() throws Exception {
        // Initialize the database
        claimCarrierRefundStatusRepository.saveAndFlush(claimCarrierRefundStatus);

        int databaseSizeBeforeDelete = claimCarrierRefundStatusRepository.findAll().size();

        // Delete the claimCarrierRefundStatus
        restClaimCarrierRefundStatusMockMvc.perform(delete("/api/claim-carrier-refund-statuses/{id}", claimCarrierRefundStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimCarrierRefundStatus> claimCarrierRefundStatusList = claimCarrierRefundStatusRepository.findAll();
        assertThat(claimCarrierRefundStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
