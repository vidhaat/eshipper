package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimAssigneeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimAssignee.class);
        ClaimAssignee claimAssignee1 = new ClaimAssignee();
        claimAssignee1.setId(1L);
        ClaimAssignee claimAssignee2 = new ClaimAssignee();
        claimAssignee2.setId(claimAssignee1.getId());
        assertThat(claimAssignee1).isEqualTo(claimAssignee2);
        claimAssignee2.setId(2L);
        assertThat(claimAssignee1).isNotEqualTo(claimAssignee2);
        claimAssignee1.setId(null);
        assertThat(claimAssignee1).isNotEqualTo(claimAssignee2);
    }
}
