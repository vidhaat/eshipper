package com.eshipper.repository;

import com.eshipper.domain.ShippingClaim;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShippingClaim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShippingClaimRepository extends JpaRepository<ShippingClaim, Long> {

}
