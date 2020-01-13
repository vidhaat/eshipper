package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCommentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimComment.class);
        ClaimComment claimComment1 = new ClaimComment();
        claimComment1.setId(1L);
        ClaimComment claimComment2 = new ClaimComment();
        claimComment2.setId(claimComment1.getId());
        assertThat(claimComment1).isEqualTo(claimComment2);
        claimComment2.setId(2L);
        assertThat(claimComment1).isNotEqualTo(claimComment2);
        claimComment1.setId(null);
        assertThat(claimComment1).isNotEqualTo(claimComment2);
    }
}
