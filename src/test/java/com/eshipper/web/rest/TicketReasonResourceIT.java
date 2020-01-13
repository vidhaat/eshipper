package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.TicketReason;
import com.eshipper.repository.TicketReasonRepository;
import com.eshipper.service.TicketReasonService;
import com.eshipper.service.dto.TicketReasonDTO;
import com.eshipper.service.mapper.TicketReasonMapper;
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
 * Integration tests for the {@link TicketReasonResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class TicketReasonResourceIT {

    @Autowired
    private TicketReasonRepository ticketReasonRepository;

    @Autowired
    private TicketReasonMapper ticketReasonMapper;

    @Autowired
    private TicketReasonService ticketReasonService;

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

    private MockMvc restTicketReasonMockMvc;

    private TicketReason ticketReason;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TicketReasonResource ticketReasonResource = new TicketReasonResource(ticketReasonService);
        this.restTicketReasonMockMvc = MockMvcBuilders.standaloneSetup(ticketReasonResource)
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
    public static TicketReason createEntity(EntityManager em) {
        TicketReason ticketReason = new TicketReason();
        return ticketReason;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketReason createUpdatedEntity(EntityManager em) {
        TicketReason ticketReason = new TicketReason();
        return ticketReason;
    }

    @BeforeEach
    public void initTest() {
        ticketReason = createEntity(em);
    }

    @Test
    @Transactional
    public void createTicketReason() throws Exception {
        int databaseSizeBeforeCreate = ticketReasonRepository.findAll().size();

        // Create the TicketReason
        TicketReasonDTO ticketReasonDTO = ticketReasonMapper.toDto(ticketReason);
        restTicketReasonMockMvc.perform(post("/api/ticket-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticketReasonDTO)))
            .andExpect(status().isCreated());

        // Validate the TicketReason in the database
        List<TicketReason> ticketReasonList = ticketReasonRepository.findAll();
        assertThat(ticketReasonList).hasSize(databaseSizeBeforeCreate + 1);
        TicketReason testTicketReason = ticketReasonList.get(ticketReasonList.size() - 1);
    }

    @Test
    @Transactional
    public void createTicketReasonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ticketReasonRepository.findAll().size();

        // Create the TicketReason with an existing ID
        ticketReason.setId(1L);
        TicketReasonDTO ticketReasonDTO = ticketReasonMapper.toDto(ticketReason);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketReasonMockMvc.perform(post("/api/ticket-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticketReasonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketReason in the database
        List<TicketReason> ticketReasonList = ticketReasonRepository.findAll();
        assertThat(ticketReasonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTicketReasons() throws Exception {
        // Initialize the database
        ticketReasonRepository.saveAndFlush(ticketReason);

        // Get all the ticketReasonList
        restTicketReasonMockMvc.perform(get("/api/ticket-reasons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticketReason.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTicketReason() throws Exception {
        // Initialize the database
        ticketReasonRepository.saveAndFlush(ticketReason);

        // Get the ticketReason
        restTicketReasonMockMvc.perform(get("/api/ticket-reasons/{id}", ticketReason.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ticketReason.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTicketReason() throws Exception {
        // Get the ticketReason
        restTicketReasonMockMvc.perform(get("/api/ticket-reasons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTicketReason() throws Exception {
        // Initialize the database
        ticketReasonRepository.saveAndFlush(ticketReason);

        int databaseSizeBeforeUpdate = ticketReasonRepository.findAll().size();

        // Update the ticketReason
        TicketReason updatedTicketReason = ticketReasonRepository.findById(ticketReason.getId()).get();
        // Disconnect from session so that the updates on updatedTicketReason are not directly saved in db
        em.detach(updatedTicketReason);
        TicketReasonDTO ticketReasonDTO = ticketReasonMapper.toDto(updatedTicketReason);

        restTicketReasonMockMvc.perform(put("/api/ticket-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticketReasonDTO)))
            .andExpect(status().isOk());

        // Validate the TicketReason in the database
        List<TicketReason> ticketReasonList = ticketReasonRepository.findAll();
        assertThat(ticketReasonList).hasSize(databaseSizeBeforeUpdate);
        TicketReason testTicketReason = ticketReasonList.get(ticketReasonList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTicketReason() throws Exception {
        int databaseSizeBeforeUpdate = ticketReasonRepository.findAll().size();

        // Create the TicketReason
        TicketReasonDTO ticketReasonDTO = ticketReasonMapper.toDto(ticketReason);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketReasonMockMvc.perform(put("/api/ticket-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ticketReasonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketReason in the database
        List<TicketReason> ticketReasonList = ticketReasonRepository.findAll();
        assertThat(ticketReasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTicketReason() throws Exception {
        // Initialize the database
        ticketReasonRepository.saveAndFlush(ticketReason);

        int databaseSizeBeforeDelete = ticketReasonRepository.findAll().size();

        // Delete the ticketReason
        restTicketReasonMockMvc.perform(delete("/api/ticket-reasons/{id}", ticketReason.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TicketReason> ticketReasonList = ticketReasonRepository.findAll();
        assertThat(ticketReasonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
