package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShippingClaimDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingClaimDTO.class);
        ShippingClaimDTO shippingClaimDTO1 = new ShippingClaimDTO();
        shippingClaimDTO1.setId(1L);
        ShippingClaimDTO shippingClaimDTO2 = new ShippingClaimDTO();
        assertThat(shippingClaimDTO1).isNotEqualTo(shippingClaimDTO2);
        shippingClaimDTO2.setId(shippingClaimDTO1.getId());
        assertThat(shippingClaimDTO1).isEqualTo(shippingClaimDTO2);
        shippingClaimDTO2.setId(2L);
        assertThat(shippingClaimDTO1).isNotEqualTo(shippingClaimDTO2);
        shippingClaimDTO1.setId(null);
        assertThat(shippingClaimDTO1).isNotEqualTo(shippingClaimDTO2);
    }
}
