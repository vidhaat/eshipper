package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimEshipperRefundDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimEshipperRefundDTO.class);
        ClaimEshipperRefundDTO claimEshipperRefundDTO1 = new ClaimEshipperRefundDTO();
        claimEshipperRefundDTO1.setId(1L);
        ClaimEshipperRefundDTO claimEshipperRefundDTO2 = new ClaimEshipperRefundDTO();
        assertThat(claimEshipperRefundDTO1).isNotEqualTo(claimEshipperRefundDTO2);
        claimEshipperRefundDTO2.setId(claimEshipperRefundDTO1.getId());
        assertThat(claimEshipperRefundDTO1).isEqualTo(claimEshipperRefundDTO2);
        claimEshipperRefundDTO2.setId(2L);
        assertThat(claimEshipperRefundDTO1).isNotEqualTo(claimEshipperRefundDTO2);
        claimEshipperRefundDTO1.setId(null);
        assertThat(claimEshipperRefundDTO1).isNotEqualTo(claimEshipperRefundDTO2);
    }
}
