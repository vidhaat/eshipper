package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimAttachment;
import com.eshipper.repository.ClaimAttachmentRepository;
import com.eshipper.service.ClaimAttachmentService;
import com.eshipper.service.dto.ClaimAttachmentDTO;
import com.eshipper.service.mapper.ClaimAttachmentMapper;
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
 * Integration tests for the {@link ClaimAttachmentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimAttachmentResourceIT {

    private static final String DEFAULT_ATTACHMENT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_PATH = "BBBBBBBBBB";

    @Autowired
    private ClaimAttachmentRepository claimAttachmentRepository;

    @Autowired
    private ClaimAttachmentMapper claimAttachmentMapper;

    @Autowired
    private ClaimAttachmentService claimAttachmentService;

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

    private MockMvc restClaimAttachmentMockMvc;

    private ClaimAttachment claimAttachment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimAttachmentResource claimAttachmentResource = new ClaimAttachmentResource(claimAttachmentService);
        this.restClaimAttachmentMockMvc = MockMvcBuilders.standaloneSetup(claimAttachmentResource)
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
    public static ClaimAttachment createEntity(EntityManager em) {
        ClaimAttachment claimAttachment = new ClaimAttachment()
            .attachmentPath(DEFAULT_ATTACHMENT_PATH);
        return claimAttachment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimAttachment createUpdatedEntity(EntityManager em) {
        ClaimAttachment claimAttachment = new ClaimAttachment()
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        return claimAttachment;
    }

    @BeforeEach
    public void initTest() {
        claimAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimAttachment() throws Exception {
        int databaseSizeBeforeCreate = claimAttachmentRepository.findAll().size();

        // Create the ClaimAttachment
        ClaimAttachmentDTO claimAttachmentDTO = claimAttachmentMapper.toDto(claimAttachment);
        restClaimAttachmentMockMvc.perform(post("/api/claim-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAttachmentDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimAttachment in the database
        List<ClaimAttachment> claimAttachmentList = claimAttachmentRepository.findAll();
        assertThat(claimAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimAttachment testClaimAttachment = claimAttachmentList.get(claimAttachmentList.size() - 1);
        assertThat(testClaimAttachment.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void createClaimAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimAttachmentRepository.findAll().size();

        // Create the ClaimAttachment with an existing ID
        claimAttachment.setId(1L);
        ClaimAttachmentDTO claimAttachmentDTO = claimAttachmentMapper.toDto(claimAttachment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimAttachmentMockMvc.perform(post("/api/claim-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimAttachment in the database
        List<ClaimAttachment> claimAttachmentList = claimAttachmentRepository.findAll();
        assertThat(claimAttachmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimAttachments() throws Exception {
        // Initialize the database
        claimAttachmentRepository.saveAndFlush(claimAttachment);

        // Get all the claimAttachmentList
        restClaimAttachmentMockMvc.perform(get("/api/claim-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH)));
    }
    
    @Test
    @Transactional
    public void getClaimAttachment() throws Exception {
        // Initialize the database
        claimAttachmentRepository.saveAndFlush(claimAttachment);

        // Get the claimAttachment
        restClaimAttachmentMockMvc.perform(get("/api/claim-attachments/{id}", claimAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimAttachment.getId().intValue()))
            .andExpect(jsonPath("$.attachmentPath").value(DEFAULT_ATTACHMENT_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingClaimAttachment() throws Exception {
        // Get the claimAttachment
        restClaimAttachmentMockMvc.perform(get("/api/claim-attachments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimAttachment() throws Exception {
        // Initialize the database
        claimAttachmentRepository.saveAndFlush(claimAttachment);

        int databaseSizeBeforeUpdate = claimAttachmentRepository.findAll().size();

        // Update the claimAttachment
        ClaimAttachment updatedClaimAttachment = claimAttachmentRepository.findById(claimAttachment.getId()).get();
        // Disconnect from session so that the updates on updatedClaimAttachment are not directly saved in db
        em.detach(updatedClaimAttachment);
        updatedClaimAttachment
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        ClaimAttachmentDTO claimAttachmentDTO = claimAttachmentMapper.toDto(updatedClaimAttachment);

        restClaimAttachmentMockMvc.perform(put("/api/claim-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAttachmentDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimAttachment in the database
        List<ClaimAttachment> claimAttachmentList = claimAttachmentRepository.findAll();
        assertThat(claimAttachmentList).hasSize(databaseSizeBeforeUpdate);
        ClaimAttachment testClaimAttachment = claimAttachmentList.get(claimAttachmentList.size() - 1);
        assertThat(testClaimAttachment.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimAttachment() throws Exception {
        int databaseSizeBeforeUpdate = claimAttachmentRepository.findAll().size();

        // Create the ClaimAttachment
        ClaimAttachmentDTO claimAttachmentDTO = claimAttachmentMapper.toDto(claimAttachment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimAttachmentMockMvc.perform(put("/api/claim-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimAttachment in the database
        List<ClaimAttachment> claimAttachmentList = claimAttachmentRepository.findAll();
        assertThat(claimAttachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimAttachment() throws Exception {
        // Initialize the database
        claimAttachmentRepository.saveAndFlush(claimAttachment);

        int databaseSizeBeforeDelete = claimAttachmentRepository.findAll().size();

        // Delete the claimAttachment
        restClaimAttachmentMockMvc.perform(delete("/api/claim-attachments/{id}", claimAttachment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimAttachment> claimAttachmentList = claimAttachmentRepository.findAll();
        assertThat(claimAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
