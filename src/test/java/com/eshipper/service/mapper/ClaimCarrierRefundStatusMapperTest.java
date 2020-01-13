package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimCarrierRefundStatusMapperTest {

    private ClaimCarrierRefundStatusMapper claimCarrierRefundStatusMapper;

    @BeforeEach
    public void setUp() {
        claimCarrierRefundStatusMapper = new ClaimCarrierRefundStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimCarrierRefundStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimCarrierRefundStatusMapper.fromId(null)).isNull();
    }
}
