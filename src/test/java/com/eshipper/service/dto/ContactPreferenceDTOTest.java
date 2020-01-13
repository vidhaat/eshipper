package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ContactPreferenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactPreferenceDTO.class);
        ContactPreferenceDTO contactPreferenceDTO1 = new ContactPreferenceDTO();
        contactPreferenceDTO1.setId(1L);
        ContactPreferenceDTO contactPreferenceDTO2 = new ContactPreferenceDTO();
        assertThat(contactPreferenceDTO1).isNotEqualTo(contactPreferenceDTO2);
        contactPreferenceDTO2.setId(contactPreferenceDTO1.getId());
        assertThat(contactPreferenceDTO1).isEqualTo(contactPreferenceDTO2);
        contactPreferenceDTO2.setId(2L);
        assertThat(contactPreferenceDTO1).isNotEqualTo(contactPreferenceDTO2);
        contactPreferenceDTO1.setId(null);
        assertThat(contactPreferenceDTO1).isNotEqualTo(contactPreferenceDTO2);
    }
}
