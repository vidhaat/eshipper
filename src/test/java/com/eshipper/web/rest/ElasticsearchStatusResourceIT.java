package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticsearchStatus;
import com.eshipper.repository.ElasticsearchStatusRepository;
import com.eshipper.service.ElasticsearchStatusService;
import com.eshipper.service.dto.ElasticsearchStatusDTO;
import com.eshipper.service.mapper.ElasticsearchStatusMapper;
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
 * Integration tests for the {@link ElasticsearchStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ElasticsearchStatusResourceIT {

    @Autowired
    private ElasticsearchStatusRepository elasticsearchStatusRepository;

    @Autowired
    private ElasticsearchStatusMapper elasticsearchStatusMapper;

    @Autowired
    private ElasticsearchStatusService elasticsearchStatusService;

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

    private MockMvc restElasticsearchStatusMockMvc;

    private ElasticsearchStatus elasticsearchStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElasticsearchStatusResource elasticsearchStatusResource = new ElasticsearchStatusResource(elasticsearchStatusService);
        this.restElasticsearchStatusMockMvc = MockMvcBuilders.standaloneSetup(elasticsearchStatusResource)
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
    public static ElasticsearchStatus createEntity(EntityManager em) {
        ElasticsearchStatus elasticsearchStatus = new ElasticsearchStatus();
        return elasticsearchStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticsearchStatus createUpdatedEntity(EntityManager em) {
        ElasticsearchStatus elasticsearchStatus = new ElasticsearchStatus();
        return elasticsearchStatus;
    }

    @BeforeEach
    public void initTest() {
        elasticsearchStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticsearchStatus() throws Exception {
        int databaseSizeBeforeCreate = elasticsearchStatusRepository.findAll().size();

        // Create the ElasticsearchStatus
        ElasticsearchStatusDTO elasticsearchStatusDTO = elasticsearchStatusMapper.toDto(elasticsearchStatus);
        restElasticsearchStatusMockMvc.perform(post("/api/elasticsearch-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticsearchStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticsearchStatus in the database
        List<ElasticsearchStatus> elasticsearchStatusList = elasticsearchStatusRepository.findAll();
        assertThat(elasticsearchStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticsearchStatus testElasticsearchStatus = elasticsearchStatusList.get(elasticsearchStatusList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticsearchStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticsearchStatusRepository.findAll().size();

        // Create the ElasticsearchStatus with an existing ID
        elasticsearchStatus.setId(1L);
        ElasticsearchStatusDTO elasticsearchStatusDTO = elasticsearchStatusMapper.toDto(elasticsearchStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticsearchStatusMockMvc.perform(post("/api/elasticsearch-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticsearchStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticsearchStatus in the database
        List<ElasticsearchStatus> elasticsearchStatusList = elasticsearchStatusRepository.findAll();
        assertThat(elasticsearchStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticsearchStatuses() throws Exception {
        // Initialize the database
        elasticsearchStatusRepository.saveAndFlush(elasticsearchStatus);

        // Get all the elasticsearchStatusList
        restElasticsearchStatusMockMvc.perform(get("/api/elasticsearch-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticsearchStatus.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticsearchStatus() throws Exception {
        // Initialize the database
        elasticsearchStatusRepository.saveAndFlush(elasticsearchStatus);

        // Get the elasticsearchStatus
        restElasticsearchStatusMockMvc.perform(get("/api/elasticsearch-statuses/{id}", elasticsearchStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elasticsearchStatus.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingElasticsearchStatus() throws Exception {
        // Get the elasticsearchStatus
        restElasticsearchStatusMockMvc.perform(get("/api/elasticsearch-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticsearchStatus() throws Exception {
        // Initialize the database
        elasticsearchStatusRepository.saveAndFlush(elasticsearchStatus);

        int databaseSizeBeforeUpdate = elasticsearchStatusRepository.findAll().size();

        // Update the elasticsearchStatus
        ElasticsearchStatus updatedElasticsearchStatus = elasticsearchStatusRepository.findById(elasticsearchStatus.getId()).get();
        // Disconnect from session so that the updates on updatedElasticsearchStatus are not directly saved in db
        em.detach(updatedElasticsearchStatus);
        ElasticsearchStatusDTO elasticsearchStatusDTO = elasticsearchStatusMapper.toDto(updatedElasticsearchStatus);

        restElasticsearchStatusMockMvc.perform(put("/api/elasticsearch-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticsearchStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticsearchStatus in the database
        List<ElasticsearchStatus> elasticsearchStatusList = elasticsearchStatusRepository.findAll();
        assertThat(elasticsearchStatusList).hasSize(databaseSizeBeforeUpdate);
        ElasticsearchStatus testElasticsearchStatus = elasticsearchStatusList.get(elasticsearchStatusList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticsearchStatus() throws Exception {
        int databaseSizeBeforeUpdate = elasticsearchStatusRepository.findAll().size();

        // Create the ElasticsearchStatus
        ElasticsearchStatusDTO elasticsearchStatusDTO = elasticsearchStatusMapper.toDto(elasticsearchStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticsearchStatusMockMvc.perform(put("/api/elasticsearch-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elasticsearchStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticsearchStatus in the database
        List<ElasticsearchStatus> elasticsearchStatusList = elasticsearchStatusRepository.findAll();
        assertThat(elasticsearchStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticsearchStatus() throws Exception {
        // Initialize the database
        elasticsearchStatusRepository.saveAndFlush(elasticsearchStatus);

        int databaseSizeBeforeDelete = elasticsearchStatusRepository.findAll().size();

        // Delete the elasticsearchStatus
        restElasticsearchStatusMockMvc.perform(delete("/api/elasticsearch-statuses/{id}", elasticsearchStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticsearchStatus> elasticsearchStatusList = elasticsearchStatusRepository.findAll();
        assertThat(elasticsearchStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
