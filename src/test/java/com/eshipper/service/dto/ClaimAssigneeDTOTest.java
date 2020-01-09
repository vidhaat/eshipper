package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimAssigneeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimAssigneeDTO.class);
        ClaimAssigneeDTO claimAssigneeDTO1 = new ClaimAssigneeDTO();
        claimAssigneeDTO1.setId(1L);
        ClaimAssigneeDTO claimAssigneeDTO2 = new ClaimAssigneeDTO();
        assertThat(claimAssigneeDTO1).isNotEqualTo(claimAssigneeDTO2);
        claimAssigneeDTO2.setId(claimAssigneeDTO1.getId());
        assertThat(claimAssigneeDTO1).isEqualTo(claimAssigneeDTO2);
        claimAssigneeDTO2.setId(2L);
        assertThat(claimAssigneeDTO1).isNotEqualTo(claimAssigneeDTO2);
        claimAssigneeDTO1.setId(null);
        assertThat(claimAssigneeDTO1).isNotEqualTo(claimAssigneeDTO2);
    }
}
