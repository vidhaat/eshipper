package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ElasticsearchStatusMapperTest {

    private ElasticsearchStatusMapper elasticsearchStatusMapper;

    @BeforeEach
    public void setUp() {
        elasticsearchStatusMapper = new ElasticsearchStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(elasticsearchStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticsearchStatusMapper.fromId(null)).isNull();
    }
}
