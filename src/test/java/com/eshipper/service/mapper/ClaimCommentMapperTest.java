package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimCommentMapperTest {

    private ClaimCommentMapper claimCommentMapper;

    @BeforeEach
    public void setUp() {
        claimCommentMapper = new ClaimCommentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimCommentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimCommentMapper.fromId(null)).isNull();
    }
}
