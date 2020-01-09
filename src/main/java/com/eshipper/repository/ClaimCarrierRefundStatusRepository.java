package com.eshipper.repository;

import com.eshipper.domain.ClaimCarrierRefundStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimCarrierRefundStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimCarrierRefundStatusRepository extends JpaRepository<ClaimCarrierRefundStatus, Long> {

}
