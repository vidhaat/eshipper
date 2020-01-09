package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ContactPreferenceMapperTest {

    private ContactPreferenceMapper contactPreferenceMapper;

    @BeforeEach
    public void setUp() {
        contactPreferenceMapper = new ContactPreferenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(contactPreferenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(contactPreferenceMapper.fromId(null)).isNull();
    }
}
