package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ShippingClaimMapperTest {

    private ShippingClaimMapper shippingClaimMapper;

    @BeforeEach
    public void setUp() {
        shippingClaimMapper = new ShippingClaimMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(shippingClaimMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shippingClaimMapper.fromId(null)).isNull();
    }
}
