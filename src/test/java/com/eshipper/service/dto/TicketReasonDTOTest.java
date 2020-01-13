package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class TicketReasonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketReasonDTO.class);
        TicketReasonDTO ticketReasonDTO1 = new TicketReasonDTO();
        ticketReasonDTO1.setId(1L);
        TicketReasonDTO ticketReasonDTO2 = new TicketReasonDTO();
        assertThat(ticketReasonDTO1).isNotEqualTo(ticketReasonDTO2);
        ticketReasonDTO2.setId(ticketReasonDTO1.getId());
        assertThat(ticketReasonDTO1).isEqualTo(ticketReasonDTO2);
        ticketReasonDTO2.setId(2L);
        assertThat(ticketReasonDTO1).isNotEqualTo(ticketReasonDTO2);
        ticketReasonDTO1.setId(null);
        assertThat(ticketReasonDTO1).isNotEqualTo(ticketReasonDTO2);
    }
}
