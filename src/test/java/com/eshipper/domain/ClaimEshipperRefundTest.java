package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimEshipperRefundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimEshipperRefund.class);
        ClaimEshipperRefund claimEshipperRefund1 = new ClaimEshipperRefund();
        claimEshipperRefund1.setId(1L);
        ClaimEshipperRefund claimEshipperRefund2 = new ClaimEshipperRefund();
        claimEshipperRefund2.setId(claimEshipperRefund1.getId());
        assertThat(claimEshipperRefund1).isEqualTo(claimEshipperRefund2);
        claimEshipperRefund2.setId(2L);
        assertThat(claimEshipperRefund1).isNotEqualTo(claimEshipperRefund2);
        claimEshipperRefund1.setId(null);
        assertThat(claimEshipperRefund1).isNotEqualTo(claimEshipperRefund2);
    }
}
