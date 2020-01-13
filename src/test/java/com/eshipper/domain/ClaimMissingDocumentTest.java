package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ClaimMissingDocumentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimMissingDocument.class);
        ClaimMissingDocument claimMissingDocument1 = new ClaimMissingDocument();
        claimMissingDocument1.setId(1L);
        ClaimMissingDocument claimMissingDocument2 = new ClaimMissingDocument();
        claimMissingDocument2.setId(claimMissingDocument1.getId());
        assertThat(claimMissingDocument1).isEqualTo(claimMissingDocument2);
        claimMissingDocument2.setId(2L);
        assertThat(claimMissingDocument1).isNotEqualTo(claimMissingDocument2);
        claimMissingDocument1.setId(null);
        assertThat(claimMissingDocument1).isNotEqualTo(claimMissingDocument2);
    }
}
