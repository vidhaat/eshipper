package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimStatusMapperTest {

    private ClaimStatusMapper claimStatusMapper;

    @BeforeEach
    public void setUp() {
        claimStatusMapper = new ClaimStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimStatusMapper.fromId(null)).isNull();
    }
}
