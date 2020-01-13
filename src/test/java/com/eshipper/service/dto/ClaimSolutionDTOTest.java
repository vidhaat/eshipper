package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimSolutionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimSolutionDTO.class);
        ClaimSolutionDTO claimSolutionDTO1 = new ClaimSolutionDTO();
        claimSolutionDTO1.setId(1L);
        ClaimSolutionDTO claimSolutionDTO2 = new ClaimSolutionDTO();
        assertThat(claimSolutionDTO1).isNotEqualTo(claimSolutionDTO2);
        claimSolutionDTO2.setId(claimSolutionDTO1.getId());
        assertThat(claimSolutionDTO1).isEqualTo(claimSolutionDTO2);
        claimSolutionDTO2.setId(2L);
        assertThat(claimSolutionDTO1).isNotEqualTo(claimSolutionDTO2);
        claimSolutionDTO1.setId(null);
        assertThat(claimSolutionDTO1).isNotEqualTo(claimSolutionDTO2);
    }
}
