package com.eshipper.repository;

import com.eshipper.domain.TicketReason;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TicketReason entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketReasonRepository extends JpaRepository<TicketReason, Long> {

}
