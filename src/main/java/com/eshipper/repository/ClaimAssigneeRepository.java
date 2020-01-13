package com.eshipper.repository;

import com.eshipper.domain.ClaimAssignee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimAssignee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimAssigneeRepository extends JpaRepository<ClaimAssignee, Long> {

}
