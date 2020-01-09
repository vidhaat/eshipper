package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCarrierRefundStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimCarrierRefundStatusDTO.class);
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO1 = new ClaimCarrierRefundStatusDTO();
        claimCarrierRefundStatusDTO1.setId(1L);
        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO2 = new ClaimCarrierRefundStatusDTO();
        assertThat(claimCarrierRefundStatusDTO1).isNotEqualTo(claimCarrierRefundStatusDTO2);
        claimCarrierRefundStatusDTO2.setId(claimCarrierRefundStatusDTO1.getId());
        assertThat(claimCarrierRefundStatusDTO1).isEqualTo(claimCarrierRefundStatusDTO2);
        claimCarrierRefundStatusDTO2.setId(2L);
        assertThat(claimCarrierRefundStatusDTO1).isNotEqualTo(claimCarrierRefundStatusDTO2);
        claimCarrierRefundStatusDTO1.setId(null);
        assertThat(claimCarrierRefundStatusDTO1).isNotEqualTo(claimCarrierRefundStatusDTO2);
    }
}
