package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ShippingClaim;
import com.eshipper.repository.ShippingClaimRepository;
import com.eshipper.service.ShippingClaimService;
import com.eshipper.service.dto.ShippingClaimDTO;
import com.eshipper.service.mapper.ShippingClaimMapper;
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
 * Integration tests for the {@link ShippingClaimResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ShippingClaimResourceIT {

    private static final ZonedDateTime DEFAULT_RECEIVED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RECEIVED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_MAILED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MAILED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TRACKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFY_CUSTOMER = false;
    private static final Boolean UPDATED_NOTIFY_CUSTOMER = true;

    private static final Boolean DEFAULT_MISSING_DOCUMENTS = false;
    private static final Boolean UPDATED_MISSING_DOCUMENTS = true;

    @Autowired
    private ShippingClaimRepository shippingClaimRepository;

    @Autowired
    private ShippingClaimMapper shippingClaimMapper;

    @Autowired
    private ShippingClaimService shippingClaimService;

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

    private MockMvc restShippingClaimMockMvc;

    private ShippingClaim shippingClaim;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingClaimResource shippingClaimResource = new ShippingClaimResource(shippingClaimService);
        this.restShippingClaimMockMvc = MockMvcBuilders.standaloneSetup(shippingClaimResource)
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
    public static ShippingClaim createEntity(EntityManager em) {
        ShippingClaim shippingClaim = new ShippingClaim()
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .mailedDate(DEFAULT_MAILED_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .trackingNumber(DEFAULT_TRACKING_NUMBER)
            .subject(DEFAULT_SUBJECT)
            .description(DEFAULT_DESCRIPTION)
            .notifyCustomer(DEFAULT_NOTIFY_CUSTOMER)
            .missingDocuments(DEFAULT_MISSING_DOCUMENTS);
        return shippingClaim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingClaim createUpdatedEntity(EntityManager em) {
        ShippingClaim shippingClaim = new ShippingClaim()
            .receivedDate(UPDATED_RECEIVED_DATE)
            .mailedDate(UPDATED_MAILED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .trackingNumber(UPDATED_TRACKING_NUMBER)
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION)
            .notifyCustomer(UPDATED_NOTIFY_CUSTOMER)
            .missingDocuments(UPDATED_MISSING_DOCUMENTS);
        return shippingClaim;
    }

    @BeforeEach
    public void initTest() {
        shippingClaim = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingClaim() throws Exception {
        int databaseSizeBeforeCreate = shippingClaimRepository.findAll().size();

        // Create the ShippingClaim
        ShippingClaimDTO shippingClaimDTO = shippingClaimMapper.toDto(shippingClaim);
        restShippingClaimMockMvc.perform(post("/api/shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingClaimDTO)))
            .andExpect(status().isCreated());

        // Validate the ShippingClaim in the database
        List<ShippingClaim> shippingClaimList = shippingClaimRepository.findAll();
        assertThat(shippingClaimList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingClaim testShippingClaim = shippingClaimList.get(shippingClaimList.size() - 1);
        assertThat(testShippingClaim.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testShippingClaim.getMailedDate()).isEqualTo(DEFAULT_MAILED_DATE);
        assertThat(testShippingClaim.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testShippingClaim.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testShippingClaim.getTrackingNumber()).isEqualTo(DEFAULT_TRACKING_NUMBER);
        assertThat(testShippingClaim.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testShippingClaim.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShippingClaim.isNotifyCustomer()).isEqualTo(DEFAULT_NOTIFY_CUSTOMER);
        assertThat(testShippingClaim.isMissingDocuments()).isEqualTo(DEFAULT_MISSING_DOCUMENTS);
    }

    @Test
    @Transactional
    public void createShippingClaimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingClaimRepository.findAll().size();

        // Create the ShippingClaim with an existing ID
        shippingClaim.setId(1L);
        ShippingClaimDTO shippingClaimDTO = shippingClaimMapper.toDto(shippingClaim);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingClaimMockMvc.perform(post("/api/shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingClaim in the database
        List<ShippingClaim> shippingClaimList = shippingClaimRepository.findAll();
        assertThat(shippingClaimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShippingClaims() throws Exception {
        // Initialize the database
        shippingClaimRepository.saveAndFlush(shippingClaim);

        // Get all the shippingClaimList
        restShippingClaimMockMvc.perform(get("/api/shipping-claims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingClaim.getId().intValue())))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(sameInstant(DEFAULT_RECEIVED_DATE))))
            .andExpect(jsonPath("$.[*].mailedDate").value(hasItem(sameInstant(DEFAULT_MAILED_DATE))))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(sameInstant(DEFAULT_UPDATED_DATE))))
            .andExpect(jsonPath("$.[*].trackingNumber").value(hasItem(DEFAULT_TRACKING_NUMBER)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].notifyCustomer").value(hasItem(DEFAULT_NOTIFY_CUSTOMER.booleanValue())))
            .andExpect(jsonPath("$.[*].missingDocuments").value(hasItem(DEFAULT_MISSING_DOCUMENTS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getShippingClaim() throws Exception {
        // Initialize the database
        shippingClaimRepository.saveAndFlush(shippingClaim);

        // Get the shippingClaim
        restShippingClaimMockMvc.perform(get("/api/shipping-claims/{id}", shippingClaim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shippingClaim.getId().intValue()))
            .andExpect(jsonPath("$.receivedDate").value(sameInstant(DEFAULT_RECEIVED_DATE)))
            .andExpect(jsonPath("$.mailedDate").value(sameInstant(DEFAULT_MAILED_DATE)))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.updatedDate").value(sameInstant(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.trackingNumber").value(DEFAULT_TRACKING_NUMBER))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.notifyCustomer").value(DEFAULT_NOTIFY_CUSTOMER.booleanValue()))
            .andExpect(jsonPath("$.missingDocuments").value(DEFAULT_MISSING_DOCUMENTS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingShippingClaim() throws Exception {
        // Get the shippingClaim
        restShippingClaimMockMvc.perform(get("/api/shipping-claims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingClaim() throws Exception {
        // Initialize the database
        shippingClaimRepository.saveAndFlush(shippingClaim);

        int databaseSizeBeforeUpdate = shippingClaimRepository.findAll().size();

        // Update the shippingClaim
        ShippingClaim updatedShippingClaim = shippingClaimRepository.findById(shippingClaim.getId()).get();
        // Disconnect from session so that the updates on updatedShippingClaim are not directly saved in db
        em.detach(updatedShippingClaim);
        updatedShippingClaim
            .receivedDate(UPDATED_RECEIVED_DATE)
            .mailedDate(UPDATED_MAILED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .trackingNumber(UPDATED_TRACKING_NUMBER)
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION)
            .notifyCustomer(UPDATED_NOTIFY_CUSTOMER)
            .missingDocuments(UPDATED_MISSING_DOCUMENTS);
        ShippingClaimDTO shippingClaimDTO = shippingClaimMapper.toDto(updatedShippingClaim);

        restShippingClaimMockMvc.perform(put("/api/shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingClaimDTO)))
            .andExpect(status().isOk());

        // Validate the ShippingClaim in the database
        List<ShippingClaim> shippingClaimList = shippingClaimRepository.findAll();
        assertThat(shippingClaimList).hasSize(databaseSizeBeforeUpdate);
        ShippingClaim testShippingClaim = shippingClaimList.get(shippingClaimList.size() - 1);
        assertThat(testShippingClaim.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testShippingClaim.getMailedDate()).isEqualTo(UPDATED_MAILED_DATE);
        assertThat(testShippingClaim.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testShippingClaim.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testShippingClaim.getTrackingNumber()).isEqualTo(UPDATED_TRACKING_NUMBER);
        assertThat(testShippingClaim.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testShippingClaim.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShippingClaim.isNotifyCustomer()).isEqualTo(UPDATED_NOTIFY_CUSTOMER);
        assertThat(testShippingClaim.isMissingDocuments()).isEqualTo(UPDATED_MISSING_DOCUMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingClaim() throws Exception {
        int databaseSizeBeforeUpdate = shippingClaimRepository.findAll().size();

        // Create the ShippingClaim
        ShippingClaimDTO shippingClaimDTO = shippingClaimMapper.toDto(shippingClaim);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingClaimMockMvc.perform(put("/api/shipping-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingClaim in the database
        List<ShippingClaim> shippingClaimList = shippingClaimRepository.findAll();
        assertThat(shippingClaimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingClaim() throws Exception {
        // Initialize the database
        shippingClaimRepository.saveAndFlush(shippingClaim);

        int databaseSizeBeforeDelete = shippingClaimRepository.findAll().size();

        // Delete the shippingClaim
        restShippingClaimMockMvc.perform(delete("/api/shipping-claims/{id}", shippingClaim.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShippingClaim> shippingClaimList = shippingClaimRepository.findAll();
        assertThat(shippingClaimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
