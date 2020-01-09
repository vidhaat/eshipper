package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class TicketReasonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketReason.class);
        TicketReason ticketReason1 = new TicketReason();
        ticketReason1.setId(1L);
        TicketReason ticketReason2 = new TicketReason();
        ticketReason2.setId(ticketReason1.getId());
        assertThat(ticketReason1).isEqualTo(ticketReason2);
        ticketReason2.setId(2L);
        assertThat(ticketReason1).isNotEqualTo(ticketReason2);
        ticketReason1.setId(null);
        assertThat(ticketReason1).isNotEqualTo(ticketReason2);
    }
}
