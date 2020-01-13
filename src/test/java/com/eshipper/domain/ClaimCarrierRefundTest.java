package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCarrierRefundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimCarrierRefund.class);
        ClaimCarrierRefund claimCarrierRefund1 = new ClaimCarrierRefund();
        claimCarrierRefund1.setId(1L);
        ClaimCarrierRefund claimCarrierRefund2 = new ClaimCarrierRefund();
        claimCarrierRefund2.setId(claimCarrierRefund1.getId());
        assertThat(claimCarrierRefund1).isEqualTo(claimCarrierRefund2);
        claimCarrierRefund2.setId(2L);
        assertThat(claimCarrierRefund1).isNotEqualTo(claimCarrierRefund2);
        claimCarrierRefund1.setId(null);
        assertThat(claimCarrierRefund1).isNotEqualTo(claimCarrierRefund2);
    }
}
