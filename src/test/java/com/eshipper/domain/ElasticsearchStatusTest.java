package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticsearchStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticsearchStatus.class);
        ElasticsearchStatus elasticsearchStatus1 = new ElasticsearchStatus();
        elasticsearchStatus1.setId(1L);
        ElasticsearchStatus elasticsearchStatus2 = new ElasticsearchStatus();
        elasticsearchStatus2.setId(elasticsearchStatus1.getId());
        assertThat(elasticsearchStatus1).isEqualTo(elasticsearchStatus2);
        elasticsearchStatus2.setId(2L);
        assertThat(elasticsearchStatus1).isNotEqualTo(elasticsearchStatus2);
        elasticsearchStatus1.setId(null);
        assertThat(elasticsearchStatus1).isNotEqualTo(elasticsearchStatus2);
    }
}
