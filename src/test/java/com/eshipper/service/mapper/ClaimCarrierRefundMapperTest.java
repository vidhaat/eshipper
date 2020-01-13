package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimCarrierRefundMapperTest {

    private ClaimCarrierRefundMapper claimCarrierRefundMapper;

    @BeforeEach
    public void setUp() {
        claimCarrierRefundMapper = new ClaimCarrierRefundMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimCarrierRefundMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimCarrierRefundMapper.fromId(null)).isNull();
    }
}
