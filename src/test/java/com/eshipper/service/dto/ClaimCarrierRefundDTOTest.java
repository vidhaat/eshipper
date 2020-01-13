package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCarrierRefundDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimCarrierRefundDTO.class);
        ClaimCarrierRefundDTO claimCarrierRefundDTO1 = new ClaimCarrierRefundDTO();
        claimCarrierRefundDTO1.setId(1L);
        ClaimCarrierRefundDTO claimCarrierRefundDTO2 = new ClaimCarrierRefundDTO();
        assertThat(claimCarrierRefundDTO1).isNotEqualTo(claimCarrierRefundDTO2);
        claimCarrierRefundDTO2.setId(claimCarrierRefundDTO1.getId());
        assertThat(claimCarrierRefundDTO1).isEqualTo(claimCarrierRefundDTO2);
        claimCarrierRefundDTO2.setId(2L);
        assertThat(claimCarrierRefundDTO1).isNotEqualTo(claimCarrierRefundDTO2);
        claimCarrierRefundDTO1.setId(null);
        assertThat(claimCarrierRefundDTO1).isNotEqualTo(claimCarrierRefundDTO2);
    }
}
