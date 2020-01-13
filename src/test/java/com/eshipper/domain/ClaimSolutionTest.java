package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimSolutionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimSolution.class);
        ClaimSolution claimSolution1 = new ClaimSolution();
        claimSolution1.setId(1L);
        ClaimSolution claimSolution2 = new ClaimSolution();
        claimSolution2.setId(claimSolution1.getId());
        assertThat(claimSolution1).isEqualTo(claimSolution2);
        claimSolution2.setId(2L);
        assertThat(claimSolution1).isNotEqualTo(claimSolution2);
        claimSolution1.setId(null);
        assertThat(claimSolution1).isNotEqualTo(claimSolution2);
    }
}
