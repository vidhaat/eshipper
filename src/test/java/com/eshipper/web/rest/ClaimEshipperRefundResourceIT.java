package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimEshipperRefund;
import com.eshipper.repository.ClaimEshipperRefundRepository;
import com.eshipper.service.ClaimEshipperRefundService;
import com.eshipper.service.dto.ClaimEshipperRefundDTO;
import com.eshipper.service.mapper.ClaimEshipperRefundMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.sameInstant;
import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ClaimEshipperRefundResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimEshipperRefundResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_CHEQUE = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ClaimEshipperRefundRepository claimEshipperRefundRepository;

    @Autowired
    private ClaimEshipperRefundMapper claimEshipperRefundMapper;

    @Autowired
    private ClaimEshipperRefundService claimEshipperRefundService;

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

    private MockMvc restClaimEshipperRefundMockMvc;

    private ClaimEshipperRefund claimEshipperRefund;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimEshipperRefundResource claimEshipperRefundResource = new ClaimEshipperRefundResource(claimEshipperRefundService);
        this.restClaimEshipperRefundMockMvc = MockMvcBuilders.standaloneSetup(claimEshipperRefundResource)
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
    public static ClaimEshipperRefund createEntity(EntityManager em) {
        ClaimEshipperRefund claimEshipperRefund = new ClaimEshipperRefund()
            .amount(DEFAULT_AMOUNT)
            .cheque(DEFAULT_CHEQUE)
            .date(DEFAULT_DATE);
        return claimEshipperRefund;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimEshipperRefund createUpdatedEntity(EntityManager em) {
        ClaimEshipperRefund claimEshipperRefund = new ClaimEshipperRefund()
            .amount(UPDATED_AMOUNT)
            .cheque(UPDATED_CHEQUE)
            .date(UPDATED_DATE);
        return claimEshipperRefund;
    }

    @BeforeEach
    public void initTest() {
        claimEshipperRefund = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimEshipperRefund() throws Exception {
        int databaseSizeBeforeCreate = claimEshipperRefundRepository.findAll().size();

        // Create the ClaimEshipperRefund
        ClaimEshipperRefundDTO claimEshipperRefundDTO = claimEshipperRefundMapper.toDto(claimEshipperRefund);
        restClaimEshipperRefundMockMvc.perform(post("/api/claim-eshipper-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimEshipperRefundDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimEshipperRefund in the database
        List<ClaimEshipperRefund> claimEshipperRefundList = claimEshipperRefundRepository.findAll();
        assertThat(claimEshipperRefundList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimEshipperRefund testClaimEshipperRefund = claimEshipperRefundList.get(claimEshipperRefundList.size() - 1);
        assertThat(testClaimEshipperRefund.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testClaimEshipperRefund.getCheque()).isEqualTo(DEFAULT_CHEQUE);
        assertThat(testClaimEshipperRefund.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createClaimEshipperRefundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimEshipperRefundRepository.findAll().size();

        // Create the ClaimEshipperRefund with an existing ID
        claimEshipperRefund.setId(1L);
        ClaimEshipperRefundDTO claimEshipperRefundDTO = claimEshipperRefundMapper.toDto(claimEshipperRefund);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimEshipperRefundMockMvc.perform(post("/api/claim-eshipper-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimEshipperRefundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimEshipperRefund in the database
        List<ClaimEshipperRefund> claimEshipperRefundList = claimEshipperRefundRepository.findAll();
        assertThat(claimEshipperRefundList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimEshipperRefunds() throws Exception {
        // Initialize the database
        claimEshipperRefundRepository.saveAndFlush(claimEshipperRefund);

        // Get all the claimEshipperRefundList
        restClaimEshipperRefundMockMvc.perform(get("/api/claim-eshipper-refunds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimEshipperRefund.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cheque").value(hasItem(DEFAULT_CHEQUE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))));
    }
    
    @Test
    @Transactional
    public void getClaimEshipperRefund() throws Exception {
        // Initialize the database
        claimEshipperRefundRepository.saveAndFlush(claimEshipperRefund);

        // Get the claimEshipperRefund
        restClaimEshipperRefundMockMvc.perform(get("/api/claim-eshipper-refunds/{id}", claimEshipperRefund.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimEshipperRefund.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.cheque").value(DEFAULT_CHEQUE))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingClaimEshipperRefund() throws Exception {
        // Get the claimEshipperRefund
        restClaimEshipperRefundMockMvc.perform(get("/api/claim-eshipper-refunds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimEshipperRefund() throws Exception {
        // Initialize the database
        claimEshipperRefundRepository.saveAndFlush(claimEshipperRefund);

        int databaseSizeBeforeUpdate = claimEshipperRefundRepository.findAll().size();

        // Update the claimEshipperRefund
        ClaimEshipperRefund updatedClaimEshipperRefund = claimEshipperRefundRepository.findById(claimEshipperRefund.getId()).get();
        // Disconnect from session so that the updates on updatedClaimEshipperRefund are not directly saved in db
        em.detach(updatedClaimEshipperRefund);
        updatedClaimEshipperRefund
            .amount(UPDATED_AMOUNT)
            .cheque(UPDATED_CHEQUE)
            .date(UPDATED_DATE);
        ClaimEshipperRefundDTO claimEshipperRefundDTO = claimEshipperRefundMapper.toDto(updatedClaimEshipperRefund);

        restClaimEshipperRefundMockMvc.perform(put("/api/claim-eshipper-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimEshipperRefundDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimEshipperRefund in the database
        List<ClaimEshipperRefund> claimEshipperRefundList = claimEshipperRefundRepository.findAll();
        assertThat(claimEshipperRefundList).hasSize(databaseSizeBeforeUpdate);
        ClaimEshipperRefund testClaimEshipperRefund = claimEshipperRefundList.get(claimEshipperRefundList.size() - 1);
        assertThat(testClaimEshipperRefund.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testClaimEshipperRefund.getCheque()).isEqualTo(UPDATED_CHEQUE);
        assertThat(testClaimEshipperRefund.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimEshipperRefund() throws Exception {
        int databaseSizeBeforeUpdate = claimEshipperRefundRepository.findAll().size();

        // Create the ClaimEshipperRefund
        ClaimEshipperRefundDTO claimEshipperRefundDTO = claimEshipperRefundMapper.toDto(claimEshipperRefund);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimEshipperRefundMockMvc.perform(put("/api/claim-eshipper-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimEshipperRefundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimEshipperRefund in the database
        List<ClaimEshipperRefund> claimEshipperRefundList = claimEshipperRefundRepository.findAll();
        assertThat(claimEshipperRefundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimEshipperRefund() throws Exception {
        // Initialize the database
        claimEshipperRefundRepository.saveAndFlush(claimEshipperRefund);

        int databaseSizeBeforeDelete = claimEshipperRefundRepository.findAll().size();

        // Delete the claimEshipperRefund
        restClaimEshipperRefundMockMvc.perform(delete("/api/claim-eshipper-refunds/{id}", claimEshipperRefund.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimEshipperRefund> claimEshipperRefundList = claimEshipperRefundRepository.findAll();
        assertThat(claimEshipperRefundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
