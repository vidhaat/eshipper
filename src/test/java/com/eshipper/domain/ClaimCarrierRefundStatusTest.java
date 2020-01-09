package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCarrierRefundStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimCarrierRefundStatus.class);
        ClaimCarrierRefundStatus claimCarrierRefundStatus1 = new ClaimCarrierRefundStatus();
        claimCarrierRefundStatus1.setId(1L);
        ClaimCarrierRefundStatus claimCarrierRefundStatus2 = new ClaimCarrierRefundStatus();
        claimCarrierRefundStatus2.setId(claimCarrierRefundStatus1.getId());
        assertThat(claimCarrierRefundStatus1).isEqualTo(claimCarrierRefundStatus2);
        claimCarrierRefundStatus2.setId(2L);
        assertThat(claimCarrierRefundStatus1).isNotEqualTo(claimCarrierRefundStatus2);
        claimCarrierRefundStatus1.setId(null);
        assertThat(claimCarrierRefundStatus1).isNotEqualTo(claimCarrierRefundStatus2);
    }
}
