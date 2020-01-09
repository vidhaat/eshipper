package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ElasticShippingClaimMapperTest {

    private ElasticShippingClaimMapper elasticShippingClaimMapper;

    @BeforeEach
    public void setUp() {
        elasticShippingClaimMapper = new ElasticShippingClaimMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(elasticShippingClaimMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticShippingClaimMapper.fromId(null)).isNull();
    }
}
