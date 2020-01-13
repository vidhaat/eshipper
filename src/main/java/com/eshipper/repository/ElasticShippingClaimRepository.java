package com.eshipper.repository;

import com.eshipper.domain.ElasticShippingClaim;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ElasticShippingClaim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticShippingClaimRepository extends JpaRepository<ElasticShippingClaim, Long> {

}
