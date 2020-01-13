package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimSolutionMapperTest {

    private ClaimSolutionMapper claimSolutionMapper;

    @BeforeEach
    public void setUp() {
        claimSolutionMapper = new ClaimSolutionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimSolutionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimSolutionMapper.fromId(null)).isNull();
    }
}
