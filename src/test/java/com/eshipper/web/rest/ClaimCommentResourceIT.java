package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ClaimComment;
import com.eshipper.repository.ClaimCommentRepository;
import com.eshipper.service.ClaimCommentService;
import com.eshipper.service.dto.ClaimCommentDTO;
import com.eshipper.service.mapper.ClaimCommentMapper;
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
 * Integration tests for the {@link ClaimCommentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ClaimCommentResourceIT {

    private static final String DEFAULT_HEADER = "AAAAAAAAAA";
    private static final String UPDATED_HEADER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_COMMENT_BY = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT_BY = "BBBBBBBBBB";

    @Autowired
    private ClaimCommentRepository claimCommentRepository;

    @Autowired
    private ClaimCommentMapper claimCommentMapper;

    @Autowired
    private ClaimCommentService claimCommentService;

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

    private MockMvc restClaimCommentMockMvc;

    private ClaimComment claimComment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimCommentResource claimCommentResource = new ClaimCommentResource(claimCommentService);
        this.restClaimCommentMockMvc = MockMvcBuilders.standaloneSetup(claimCommentResource)
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
    public static ClaimComment createEntity(EntityManager em) {
        ClaimComment claimComment = new ClaimComment()
            .header(DEFAULT_HEADER)
            .description(DEFAULT_DESCRIPTION)
            .date(DEFAULT_DATE)
            .commentBy(DEFAULT_COMMENT_BY);
        return claimComment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimComment createUpdatedEntity(EntityManager em) {
        ClaimComment claimComment = new ClaimComment()
            .header(UPDATED_HEADER)
            .description(UPDATED_DESCRIPTION)
            .date(UPDATED_DATE)
            .commentBy(UPDATED_COMMENT_BY);
        return claimComment;
    }

    @BeforeEach
    public void initTest() {
        claimComment = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimComment() throws Exception {
        int databaseSizeBeforeCreate = claimCommentRepository.findAll().size();

        // Create the ClaimComment
        ClaimCommentDTO claimCommentDTO = claimCommentMapper.toDto(claimComment);
        restClaimCommentMockMvc.perform(post("/api/claim-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCommentDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimComment in the database
        List<ClaimComment> claimCommentList = claimCommentRepository.findAll();
        assertThat(claimCommentList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimComment testClaimComment = claimCommentList.get(claimCommentList.size() - 1);
        assertThat(testClaimComment.getHeader()).isEqualTo(DEFAULT_HEADER);
        assertThat(testClaimComment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testClaimComment.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testClaimComment.getCommentBy()).isEqualTo(DEFAULT_COMMENT_BY);
    }

    @Test
    @Transactional
    public void createClaimCommentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimCommentRepository.findAll().size();

        // Create the ClaimComment with an existing ID
        claimComment.setId(1L);
        ClaimCommentDTO claimCommentDTO = claimCommentMapper.toDto(claimComment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimCommentMockMvc.perform(post("/api/claim-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimComment in the database
        List<ClaimComment> claimCommentList = claimCommentRepository.findAll();
        assertThat(claimCommentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClaimComments() throws Exception {
        // Initialize the database
        claimCommentRepository.saveAndFlush(claimComment);

        // Get all the claimCommentList
        restClaimCommentMockMvc.perform(get("/api/claim-comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimComment.getId().intValue())))
            .andExpect(jsonPath("$.[*].header").value(hasItem(DEFAULT_HEADER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].commentBy").value(hasItem(DEFAULT_COMMENT_BY)));
    }
    
    @Test
    @Transactional
    public void getClaimComment() throws Exception {
        // Initialize the database
        claimCommentRepository.saveAndFlush(claimComment);

        // Get the claimComment
        restClaimCommentMockMvc.perform(get("/api/claim-comments/{id}", claimComment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimComment.getId().intValue()))
            .andExpect(jsonPath("$.header").value(DEFAULT_HEADER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.commentBy").value(DEFAULT_COMMENT_BY));
    }

    @Test
    @Transactional
    public void getNonExistingClaimComment() throws Exception {
        // Get the claimComment
        restClaimCommentMockMvc.perform(get("/api/claim-comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimComment() throws Exception {
        // Initialize the database
        claimCommentRepository.saveAndFlush(claimComment);

        int databaseSizeBeforeUpdate = claimCommentRepository.findAll().size();

        // Update the claimComment
        ClaimComment updatedClaimComment = claimCommentRepository.findById(claimComment.getId()).get();
        // Disconnect from session so that the updates on updatedClaimComment are not directly saved in db
        em.detach(updatedClaimComment);
        updatedClaimComment
            .header(UPDATED_HEADER)
            .description(UPDATED_DESCRIPTION)
            .date(UPDATED_DATE)
            .commentBy(UPDATED_COMMENT_BY);
        ClaimCommentDTO claimCommentDTO = claimCommentMapper.toDto(updatedClaimComment);

        restClaimCommentMockMvc.perform(put("/api/claim-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCommentDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimComment in the database
        List<ClaimComment> claimCommentList = claimCommentRepository.findAll();
        assertThat(claimCommentList).hasSize(databaseSizeBeforeUpdate);
        ClaimComment testClaimComment = claimCommentList.get(claimCommentList.size() - 1);
        assertThat(testClaimComment.getHeader()).isEqualTo(UPDATED_HEADER);
        assertThat(testClaimComment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testClaimComment.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testClaimComment.getCommentBy()).isEqualTo(UPDATED_COMMENT_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimComment() throws Exception {
        int databaseSizeBeforeUpdate = claimCommentRepository.findAll().size();

        // Create the ClaimComment
        ClaimCommentDTO claimCommentDTO = claimCommentMapper.toDto(claimComment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimCommentMockMvc.perform(put("/api/claim-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimComment in the database
        List<ClaimComment> claimCommentList = claimCommentRepository.findAll();
        assertThat(claimCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimComment() throws Exception {
        // Initialize the database
        claimCommentRepository.saveAndFlush(claimComment);

        int databaseSizeBeforeDelete = claimCommentRepository.findAll().size();

        // Delete the claimComment
        restClaimCommentMockMvc.perform(delete("/api/claim-comments/{id}", claimComment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimComment> claimCommentList = claimCommentRepository.findAll();
        assertThat(claimCommentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
