package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimAttachmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimAttachment.class);
        ClaimAttachment claimAttachment1 = new ClaimAttachment();
        claimAttachment1.setId(1L);
        ClaimAttachment claimAttachment2 = new ClaimAttachment();
        claimAttachment2.setId(claimAttachment1.getId());
        assertThat(claimAttachment1).isEqualTo(claimAttachment2);
        claimAttachment2.setId(2L);
        assertThat(claimAttachment1).isNotEqualTo(claimAttachment2);
        claimAttachment1.setId(null);
        assertThat(claimAttachment1).isNotEqualTo(claimAttachment2);
    }
}
