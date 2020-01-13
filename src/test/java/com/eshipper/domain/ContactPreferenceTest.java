package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ContactPreferenceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactPreference.class);
        ContactPreference contactPreference1 = new ContactPreference();
        contactPreference1.setId(1L);
        ContactPreference contactPreference2 = new ContactPreference();
        contactPreference2.setId(contactPreference1.getId());
        assertThat(contactPreference1).isEqualTo(contactPreference2);
        contactPreference2.setId(2L);
        assertThat(contactPreference1).isNotEqualTo(contactPreference2);
        contactPreference1.setId(null);
        assertThat(contactPreference1).isNotEqualTo(contactPreference2);
    }
}
