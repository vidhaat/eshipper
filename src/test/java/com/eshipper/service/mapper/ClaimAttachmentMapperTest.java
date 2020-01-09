package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimAttachmentMapperTest {

    private ClaimAttachmentMapper claimAttachmentMapper;

    @BeforeEach
    public void setUp() {
        claimAttachmentMapper = new ClaimAttachmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimAttachmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimAttachmentMapper.fromId(null)).isNull();
    }
}
