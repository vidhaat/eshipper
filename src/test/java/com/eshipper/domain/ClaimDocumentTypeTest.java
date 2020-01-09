package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimDocumentTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimDocumentType.class);
        ClaimDocumentType claimDocumentType1 = new ClaimDocumentType();
        claimDocumentType1.setId(1L);
        ClaimDocumentType claimDocumentType2 = new ClaimDocumentType();
        claimDocumentType2.setId(claimDocumentType1.getId());
        assertThat(claimDocumentType1).isEqualTo(claimDocumentType2);
        claimDocumentType2.setId(2L);
        assertThat(claimDocumentType1).isNotEqualTo(claimDocumentType2);
        claimDocumentType1.setId(null);
        assertThat(claimDocumentType1).isNotEqualTo(claimDocumentType2);
    }
}
