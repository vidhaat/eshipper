package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticShippingClaimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticShippingClaim.class);
        ElasticShippingClaim elasticShippingClaim1 = new ElasticShippingClaim();
        elasticShippingClaim1.setId(1L);
        ElasticShippingClaim elasticShippingClaim2 = new ElasticShippingClaim();
        elasticShippingClaim2.setId(elasticShippingClaim1.getId());
        assertThat(elasticShippingClaim1).isEqualTo(elasticShippingClaim2);
        elasticShippingClaim2.setId(2L);
        assertThat(elasticShippingClaim1).isNotEqualTo(elasticShippingClaim2);
        elasticShippingClaim1.setId(null);
        assertThat(elasticShippingClaim1).isNotEqualTo(elasticShippingClaim2);
    }
}
