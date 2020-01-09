package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TicketReasonMapperTest {

    private TicketReasonMapper ticketReasonMapper;

    @BeforeEach
    public void setUp() {
        ticketReasonMapper = new TicketReasonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(ticketReasonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ticketReasonMapper.fromId(null)).isNull();
    }
}
