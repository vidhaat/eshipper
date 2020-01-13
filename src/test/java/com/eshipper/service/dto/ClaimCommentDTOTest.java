package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimCommentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimCommentDTO.class);
        ClaimCommentDTO claimCommentDTO1 = new ClaimCommentDTO();
        claimCommentDTO1.setId(1L);
        ClaimCommentDTO claimCommentDTO2 = new ClaimCommentDTO();
        assertThat(claimCommentDTO1).isNotEqualTo(claimCommentDTO2);
        claimCommentDTO2.setId(claimCommentDTO1.getId());
        assertThat(claimCommentDTO1).isEqualTo(claimCommentDTO2);
        claimCommentDTO2.setId(2L);
        assertThat(claimCommentDTO1).isNotEqualTo(claimCommentDTO2);
        claimCommentDTO1.setId(null);
        assertThat(claimCommentDTO1).isNotEqualTo(claimCommentDTO2);
    }
}
