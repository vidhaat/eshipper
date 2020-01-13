package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimStatusDTO.class);
        ClaimStatusDTO claimStatusDTO1 = new ClaimStatusDTO();
        claimStatusDTO1.setId(1L);
        ClaimStatusDTO claimStatusDTO2 = new ClaimStatusDTO();
        assertThat(claimStatusDTO1).isNotEqualTo(claimStatusDTO2);
        claimStatusDTO2.setId(claimStatusDTO1.getId());
        assertThat(claimStatusDTO1).isEqualTo(claimStatusDTO2);
        claimStatusDTO2.setId(2L);
        assertThat(claimStatusDTO1).isNotEqualTo(claimStatusDTO2);
        claimStatusDTO1.setId(null);
        assertThat(claimStatusDTO1).isNotEqualTo(claimStatusDTO2);
    }
}
