package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimCarrierRefund;
import com.eshipper.repository.ClaimCarrierRefundRepository;
import com.eshipper.service.ClaimCarrierRefundService;
import com.eshipper.service.dto.ClaimCarrierRefundDTO;
import com.eshipper.service.mapper.ClaimCarrierRefundMapper;
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
 * Integration tests for the {@link ClaimCarrierRefundResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimCarrierRefundResourceIT {

    private static final String DEFAULT_CARRIER_CLAIM = "AAAAAAAAAA";
    private static final String UPDATED_CARRIER_CLAIM = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_CHEQUE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CHEQUE = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ClaimCarrierRefundRepository claimCarrierRefundRepository;

    @Autowired
    private ClaimCarrierRefundMapper claimCarrierRefundMapper;

    @Autowired
    private ClaimCarrierRefundService claimCarrierRefundService;

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

    private MockMvc restClaimCarrierRefundMockMvc;

    private ClaimCarrierRefund claimCarrierRefund;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimCarrierRefundResource claimCarrierRefundResource = new ClaimCarrierRefundResource(claimCarrierRefundService);
        this.restClaimCarrierRefundMockMvc = MockMvcBuilders.standaloneSetup(claimCarrierRefundResource)
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
    public static ClaimCarrierRefund createEntity(EntityManager em) {
        ClaimCarrierRefund claimCarrierRefund = new ClaimCarrierRefund()
            .carrierClaim(DEFAULT_CARRIER_CLAIM)
            .amount(DEFAULT_AMOUNT)
            .chequeNumber(DEFAULT_CHEQUE_NUMBER)
            .cheque(DEFAULT_CHEQUE)
            .date(DEFAULT_DATE);
        return claimCarrierRefund;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimCarrierRefund createUpdatedEntity(EntityManager em) {
        ClaimCarrierRefund claimCarrierRefund = new ClaimCarrierRefund()
            .carrierClaim(UPDATED_CARRIER_CLAIM)
            .amount(UPDATED_AMOUNT)
            .chequeNumber(UPDATED_CHEQUE_NUMBER)
            .cheque(UPDATED_CHEQUE)
            .date(UPDATED_DATE);
        return claimCarrierRefund;
    }

    @BeforeEach
    public void initTest() {
        claimCarrierRefund = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimCarrierRefund() throws Exception {
        int databaseSizeBeforeCreate = claimCarrierRefundRepository.findAll().size();

        // Create the ClaimCarrierRefund
        ClaimCarrierRefundDTO claimCarrierRefundDTO = claimCarrierRefundMapper.toDto(claimCarrierRefund);
        restClaimCarrierRefundMockMvc.perform(post("/api/claim-carrier-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimCarrierRefund in the database
        List<ClaimCarrierRefund> claimCarrierRefundList = claimCarrierRefundRepository.findAll();
        assertThat(claimCarrierRefundList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimCarrierRefund testClaimCarrierRefund = claimCarrierRefundList.get(claimCarrierRefundList.size() - 1);
        assertThat(testClaimCarrierRefund.getCarrierClaim()).isEqualTo(DEFAULT_CARRIER_CLAIM);
        assertThat(testClaimCarrierRefund.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testClaimCarrierRefund.getChequeNumber()).isEqualTo(DEFAULT_CHEQUE_NUMBER);
        assertThat(testClaimCarrierRefund.getCheque()).isEqualTo(DEFAULT_CHEQUE);
        assertThat(testClaimCarrierRefund.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createClaimCarrierRefundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimCarrierRefundRepository.findAll().size();

        // Create the ClaimCarrierRefund with an existing ID
        claimCarrierRefund.setId(1L);
        ClaimCarrierRefundDTO claimCarrierRefundDTO = claimCarrierRefundMapper.toDto(claimCarrierRefund);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimCarrierRefundMockMvc.perform(post("/api/claim-carrier-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimCarrierRefund in the database
        List<ClaimCarrierRefund> claimCarrierRefundList = claimCarrierRefundRepository.findAll();
        assertThat(claimCarrierRefundList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimCarrierRefunds() throws Exception {
        // Initialize the database
        claimCarrierRefundRepository.saveAndFlush(claimCarrierRefund);

        // Get all the claimCarrierRefundList
        restClaimCarrierRefundMockMvc.perform(get("/api/claim-carrier-refunds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimCarrierRefund.getId().intValue())))
            .andExpect(jsonPath("$.[*].carrierClaim").value(hasItem(DEFAULT_CARRIER_CLAIM)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].chequeNumber").value(hasItem(DEFAULT_CHEQUE_NUMBER)))
            .andExpect(jsonPath("$.[*].cheque").value(hasItem(DEFAULT_CHEQUE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))));
    }
    
    @Test
    @Transactional
    public void getClaimCarrierRefund() throws Exception {
        // Initialize the database
        claimCarrierRefundRepository.saveAndFlush(claimCarrierRefund);

        // Get the claimCarrierRefund
        restClaimCarrierRefundMockMvc.perform(get("/api/claim-carrier-refunds/{id}", claimCarrierRefund.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimCarrierRefund.getId().intValue()))
            .andExpect(jsonPath("$.carrierClaim").value(DEFAULT_CARRIER_CLAIM))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.chequeNumber").value(DEFAULT_CHEQUE_NUMBER))
            .andExpect(jsonPath("$.cheque").value(DEFAULT_CHEQUE))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingClaimCarrierRefund() throws Exception {
        // Get the claimCarrierRefund
        restClaimCarrierRefundMockMvc.perform(get("/api/claim-carrier-refunds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimCarrierRefund() throws Exception {
        // Initialize the database
        claimCarrierRefundRepository.saveAndFlush(claimCarrierRefund);

        int databaseSizeBeforeUpdate = claimCarrierRefundRepository.findAll().size();

        // Update the claimCarrierRefund
        ClaimCarrierRefund updatedClaimCarrierRefund = claimCarrierRefundRepository.findById(claimCarrierRefund.getId()).get();
        // Disconnect from session so that the updates on updatedClaimCarrierRefund are not directly saved in db
        em.detach(updatedClaimCarrierRefund);
        updatedClaimCarrierRefund
            .carrierClaim(UPDATED_CARRIER_CLAIM)
            .amount(UPDATED_AMOUNT)
            .chequeNumber(UPDATED_CHEQUE_NUMBER)
            .cheque(UPDATED_CHEQUE)
            .date(UPDATED_DATE);
        ClaimCarrierRefundDTO claimCarrierRefundDTO = claimCarrierRefundMapper.toDto(updatedClaimCarrierRefund);

        restClaimCarrierRefundMockMvc.perform(put("/api/claim-carrier-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimCarrierRefund in the database
        List<ClaimCarrierRefund> claimCarrierRefundList = claimCarrierRefundRepository.findAll();
        assertThat(claimCarrierRefundList).hasSize(databaseSizeBeforeUpdate);
        ClaimCarrierRefund testClaimCarrierRefund = claimCarrierRefundList.get(claimCarrierRefundList.size() - 1);
        assertThat(testClaimCarrierRefund.getCarrierClaim()).isEqualTo(UPDATED_CARRIER_CLAIM);
        assertThat(testClaimCarrierRefund.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testClaimCarrierRefund.getChequeNumber()).isEqualTo(UPDATED_CHEQUE_NUMBER);
        assertThat(testClaimCarrierRefund.getCheque()).isEqualTo(UPDATED_CHEQUE);
        assertThat(testClaimCarrierRefund.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimCarrierRefund() throws Exception {
        int databaseSizeBeforeUpdate = claimCarrierRefundRepository.findAll().size();

        // Create the ClaimCarrierRefund
        ClaimCarrierRefundDTO claimCarrierRefundDTO = claimCarrierRefundMapper.toDto(claimCarrierRefund);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimCarrierRefundMockMvc.perform(put("/api/claim-carrier-refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCarrierRefundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimCarrierRefund in the database
        List<ClaimCarrierRefund> claimCarrierRefundList = claimCarrierRefundRepository.findAll();
        assertThat(claimCarrierRefundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimCarrierRefund() throws Exception {
        // Initialize the database
        claimCarrierRefundRepository.saveAndFlush(claimCarrierRefund);

        int databaseSizeBeforeDelete = claimCarrierRefundRepository.findAll().size();

        // Delete the claimCarrierRefund
        restClaimCarrierRefundMockMvc.perform(delete("/api/claim-carrier-refunds/{id}", claimCarrierRefund.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimCarrierRefund> claimCarrierRefundList = claimCarrierRefundRepository.findAll();
        assertThat(claimCarrierRefundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
