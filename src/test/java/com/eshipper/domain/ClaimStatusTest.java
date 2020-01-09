package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimStatus.class);
        ClaimStatus claimStatus1 = new ClaimStatus();
        claimStatus1.setId(1L);
        ClaimStatus claimStatus2 = new ClaimStatus();
        claimStatus2.setId(claimStatus1.getId());
        assertThat(claimStatus1).isEqualTo(claimStatus2);
        claimStatus2.setId(2L);
        assertThat(claimStatus1).isNotEqualTo(claimStatus2);
        claimStatus1.setId(null);
        assertThat(claimStatus1).isNotEqualTo(claimStatus2);
    }
}
