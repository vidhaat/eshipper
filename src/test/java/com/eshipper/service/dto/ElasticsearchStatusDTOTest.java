package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticsearchStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticsearchStatusDTO.class);
        ElasticsearchStatusDTO elasticsearchStatusDTO1 = new ElasticsearchStatusDTO();
        elasticsearchStatusDTO1.setId(1L);
        ElasticsearchStatusDTO elasticsearchStatusDTO2 = new ElasticsearchStatusDTO();
        assertThat(elasticsearchStatusDTO1).isNotEqualTo(elasticsearchStatusDTO2);
        elasticsearchStatusDTO2.setId(elasticsearchStatusDTO1.getId());
        assertThat(elasticsearchStatusDTO1).isEqualTo(elasticsearchStatusDTO2);
        elasticsearchStatusDTO2.setId(2L);
        assertThat(elasticsearchStatusDTO1).isNotEqualTo(elasticsearchStatusDTO2);
        elasticsearchStatusDTO1.setId(null);
        assertThat(elasticsearchStatusDTO1).isNotEqualTo(elasticsearchStatusDTO2);
    }
}
