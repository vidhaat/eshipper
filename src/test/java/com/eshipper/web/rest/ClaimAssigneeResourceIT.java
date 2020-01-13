package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimAssignee;
import com.eshipper.repository.ClaimAssigneeRepository;
import com.eshipper.service.ClaimAssigneeService;
import com.eshipper.service.dto.ClaimAssigneeDTO;
import com.eshipper.service.mapper.ClaimAssigneeMapper;
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
 * Integration tests for the {@link ClaimAssigneeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimAssigneeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimAssigneeRepository claimAssigneeRepository;

    @Autowired
    private ClaimAssigneeMapper claimAssigneeMapper;

    @Autowired
    private ClaimAssigneeService claimAssigneeService;

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

    private MockMvc restClaimAssigneeMockMvc;

    private ClaimAssignee claimAssignee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimAssigneeResource claimAssigneeResource = new ClaimAssigneeResource(claimAssigneeService);
        this.restClaimAssigneeMockMvc = MockMvcBuilders.standaloneSetup(claimAssigneeResource)
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
    public static ClaimAssignee createEntity(EntityManager em) {
        ClaimAssignee claimAssignee = new ClaimAssignee()
            .name(DEFAULT_NAME);
        return claimAssignee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimAssignee createUpdatedEntity(EntityManager em) {
        ClaimAssignee claimAssignee = new ClaimAssignee()
            .name(UPDATED_NAME);
        return claimAssignee;
    }

    @BeforeEach
    public void initTest() {
        claimAssignee = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimAssignee() throws Exception {
        int databaseSizeBeforeCreate = claimAssigneeRepository.findAll().size();

        // Create the ClaimAssignee
        ClaimAssigneeDTO claimAssigneeDTO = claimAssigneeMapper.toDto(claimAssignee);
        restClaimAssigneeMockMvc.perform(post("/api/claim-assignees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAssigneeDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimAssignee in the database
        List<ClaimAssignee> claimAssigneeList = claimAssigneeRepository.findAll();
        assertThat(claimAssigneeList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimAssignee testClaimAssignee = claimAssigneeList.get(claimAssigneeList.size() - 1);
        assertThat(testClaimAssignee.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createClaimAssigneeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimAssigneeRepository.findAll().size();

        // Create the ClaimAssignee with an existing ID
        claimAssignee.setId(1L);
        ClaimAssigneeDTO claimAssigneeDTO = claimAssigneeMapper.toDto(claimAssignee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimAssigneeMockMvc.perform(post("/api/claim-assignees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAssigneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimAssignee in the database
        List<ClaimAssignee> claimAssigneeList = claimAssigneeRepository.findAll();
        assertThat(claimAssigneeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimAssignees() throws Exception {
        // Initialize the database
        claimAssigneeRepository.saveAndFlush(claimAssignee);

        // Get all the claimAssigneeList
        restClaimAssigneeMockMvc.perform(get("/api/claim-assignees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimAssignee.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getClaimAssignee() throws Exception {
        // Initialize the database
        claimAssigneeRepository.saveAndFlush(claimAssignee);

        // Get the claimAssignee
        restClaimAssigneeMockMvc.perform(get("/api/claim-assignees/{id}", claimAssignee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimAssignee.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingClaimAssignee() throws Exception {
        // Get the claimAssignee
        restClaimAssigneeMockMvc.perform(get("/api/claim-assignees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimAssignee() throws Exception {
        // Initialize the database
        claimAssigneeRepository.saveAndFlush(claimAssignee);

        int databaseSizeBeforeUpdate = claimAssigneeRepository.findAll().size();

        // Update the claimAssignee
        ClaimAssignee updatedClaimAssignee = claimAssigneeRepository.findById(claimAssignee.getId()).get();
        // Disconnect from session so that the updates on updatedClaimAssignee are not directly saved in db
        em.detach(updatedClaimAssignee);
        updatedClaimAssignee
            .name(UPDATED_NAME);
        ClaimAssigneeDTO claimAssigneeDTO = claimAssigneeMapper.toDto(updatedClaimAssignee);

        restClaimAssigneeMockMvc.perform(put("/api/claim-assignees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAssigneeDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimAssignee in the database
        List<ClaimAssignee> claimAssigneeList = claimAssigneeRepository.findAll();
        assertThat(claimAssigneeList).hasSize(databaseSizeBeforeUpdate);
        ClaimAssignee testClaimAssignee = claimAssigneeList.get(claimAssigneeList.size() - 1);
        assertThat(testClaimAssignee.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimAssignee() throws Exception {
        int databaseSizeBeforeUpdate = claimAssigneeRepository.findAll().size();

        // Create the ClaimAssignee
        ClaimAssigneeDTO claimAssigneeDTO = claimAssigneeMapper.toDto(claimAssignee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimAssigneeMockMvc.perform(put("/api/claim-assignees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAssigneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimAssignee in the database
        List<ClaimAssignee> claimAssigneeList = claimAssigneeRepository.findAll();
        assertThat(claimAssigneeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimAssignee() throws Exception {
        // Initialize the database
        claimAssigneeRepository.saveAndFlush(claimAssignee);

        int databaseSizeBeforeDelete = claimAssigneeRepository.findAll().size();

        // Delete the claimAssignee
        restClaimAssigneeMockMvc.perform(delete("/api/claim-assignees/{id}", claimAssignee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimAssignee> claimAssigneeList = claimAssigneeRepository.findAll();
        assertThat(claimAssigneeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
