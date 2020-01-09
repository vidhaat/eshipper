package com.eshipper.repository;

import com.eshipper.domain.ClaimDocumentType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimDocumentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimDocumentTypeRepository extends JpaRepository<ClaimDocumentType, Long> {

}
