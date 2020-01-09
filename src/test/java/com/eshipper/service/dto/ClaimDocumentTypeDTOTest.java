package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimDocumentTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimDocumentTypeDTO.class);
        ClaimDocumentTypeDTO claimDocumentTypeDTO1 = new ClaimDocumentTypeDTO();
        claimDocumentTypeDTO1.setId(1L);
        ClaimDocumentTypeDTO claimDocumentTypeDTO2 = new ClaimDocumentTypeDTO();
        assertThat(claimDocumentTypeDTO1).isNotEqualTo(claimDocumentTypeDTO2);
        claimDocumentTypeDTO2.setId(claimDocumentTypeDTO1.getId());
        assertThat(claimDocumentTypeDTO1).isEqualTo(claimDocumentTypeDTO2);
        claimDocumentTypeDTO2.setId(2L);
        assertThat(claimDocumentTypeDTO1).isNotEqualTo(claimDocumentTypeDTO2);
        claimDocumentTypeDTO1.setId(null);
        assertThat(claimDocumentTypeDTO1).isNotEqualTo(claimDocumentTypeDTO2);
    }
}
