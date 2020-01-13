package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticShippingClaimDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticShippingClaimDTO.class);
        ElasticShippingClaimDTO elasticShippingClaimDTO1 = new ElasticShippingClaimDTO();
        elasticShippingClaimDTO1.setId(1L);
        ElasticShippingClaimDTO elasticShippingClaimDTO2 = new ElasticShippingClaimDTO();
        assertThat(elasticShippingClaimDTO1).isNotEqualTo(elasticShippingClaimDTO2);
        elasticShippingClaimDTO2.setId(elasticShippingClaimDTO1.getId());
        assertThat(elasticShippingClaimDTO1).isEqualTo(elasticShippingClaimDTO2);
        elasticShippingClaimDTO2.setId(2L);
        assertThat(elasticShippingClaimDTO1).isNotEqualTo(elasticShippingClaimDTO2);
        elasticShippingClaimDTO1.setId(null);
        assertThat(elasticShippingClaimDTO1).isNotEqualTo(elasticShippingClaimDTO2);
    }
}
