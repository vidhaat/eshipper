package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimDocumentTypeMapperTest {

    private ClaimDocumentTypeMapper claimDocumentTypeMapper;

    @BeforeEach
    public void setUp() {
        claimDocumentTypeMapper = new ClaimDocumentTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimDocumentTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimDocumentTypeMapper.fromId(null)).isNull();
    }
}
