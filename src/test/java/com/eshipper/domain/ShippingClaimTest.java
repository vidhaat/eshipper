package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShippingClaimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingClaim.class);
        ShippingClaim shippingClaim1 = new ShippingClaim();
        shippingClaim1.setId(1L);
        ShippingClaim shippingClaim2 = new ShippingClaim();
        shippingClaim2.setId(shippingClaim1.getId());
        assertThat(shippingClaim1).isEqualTo(shippingClaim2);
        shippingClaim2.setId(2L);
        assertThat(shippingClaim1).isNotEqualTo(shippingClaim2);
        shippingClaim1.setId(null);
        assertThat(shippingClaim1).isNotEqualTo(shippingClaim2);
    }
}
