package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimMissingDocumentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimMissingDocumentDTO.class);
        ClaimMissingDocumentDTO claimMissingDocumentDTO1 = new ClaimMissingDocumentDTO();
        claimMissingDocumentDTO1.setId(1L);
        ClaimMissingDocumentDTO claimMissingDocumentDTO2 = new ClaimMissingDocumentDTO();
        assertThat(claimMissingDocumentDTO1).isNotEqualTo(claimMissingDocumentDTO2);
        claimMissingDocumentDTO2.setId(claimMissingDocumentDTO1.getId());
        assertThat(claimMissingDocumentDTO1).isEqualTo(claimMissingDocumentDTO2);
        claimMissingDocumentDTO2.setId(2L);
        assertThat(claimMissingDocumentDTO1).isNotEqualTo(claimMissingDocumentDTO2);
        claimMissingDocumentDTO1.setId(null);
        assertThat(claimMissingDocumentDTO1).isNotEqualTo(claimMissingDocumentDTO2);
    }
}
