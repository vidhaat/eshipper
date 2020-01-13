package com.eshipper.repository;

import com.eshipper.domain.ClaimStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Long> {

}
