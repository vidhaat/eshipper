package com.eshipper.repository;

import com.eshipper.domain.ClaimCarrierRefund;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimCarrierRefund entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimCarrierRefundRepository extends JpaRepository<ClaimCarrierRefund, Long> {

}
