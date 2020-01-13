package com.eshipper.repository;

import com.eshipper.domain.ContactPreference;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContactPreference entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactPreferenceRepository extends JpaRepository<ContactPreference, Long> {

}
