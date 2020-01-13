package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimAssigneeMapperTest {

    private ClaimAssigneeMapper claimAssigneeMapper;

    @BeforeEach
    public void setUp() {
        claimAssigneeMapper = new ClaimAssigneeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimAssigneeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimAssigneeMapper.fromId(null)).isNull();
    }
}
