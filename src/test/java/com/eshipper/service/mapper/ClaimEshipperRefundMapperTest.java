package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimEshipperRefundMapperTest {

    private ClaimEshipperRefundMapper claimEshipperRefundMapper;

    @BeforeEach
    public void setUp() {
        claimEshipperRefundMapper = new ClaimEshipperRefundMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimEshipperRefundMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimEshipperRefundMapper.fromId(null)).isNull();
    }
}
