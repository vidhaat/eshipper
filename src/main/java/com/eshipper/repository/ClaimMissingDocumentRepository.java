package com.eshipper.repository;

import com.eshipper.domain.ClaimMissingDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimMissingDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimMissingDocumentRepository extends JpaRepository<ClaimMissingDocument, Long> {

}
