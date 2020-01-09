package com.eshipper.repository;

import com.eshipper.domain.ClaimEshipperRefund;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimEshipperRefund entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimEshipperRefundRepository extends JpaRepository<ClaimEshipperRefund, Long> {

}
