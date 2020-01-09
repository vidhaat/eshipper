package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ClaimMissingDocumentMapperTest {

    private ClaimMissingDocumentMapper claimMissingDocumentMapper;

    @BeforeEach
    public void setUp() {
        claimMissingDocumentMapper = new ClaimMissingDocumentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(claimMissingDocumentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claimMissingDocumentMapper.fromId(null)).isNull();
    }
}
