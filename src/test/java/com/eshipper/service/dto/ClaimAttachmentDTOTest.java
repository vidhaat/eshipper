package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimAttachmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimAttachmentDTO.class);
        ClaimAttachmentDTO claimAttachmentDTO1 = new ClaimAttachmentDTO();
        claimAttachmentDTO1.setId(1L);
        ClaimAttachmentDTO claimAttachmentDTO2 = new ClaimAttachmentDTO();
        assertThat(claimAttachmentDTO1).isNotEqualTo(claimAttachmentDTO2);
        claimAttachmentDTO2.setId(claimAttachmentDTO1.getId());
        assertThat(claimAttachmentDTO1).isEqualTo(claimAttachmentDTO2);
        claimAttachmentDTO2.setId(2L);
        assertThat(claimAttachmentDTO1).isNotEqualTo(claimAttachmentDTO2);
        claimAttachmentDTO1.setId(null);
        assertThat(claimAttachmentDTO1).isNotEqualTo(claimAttachmentDTO2);
    }
}
