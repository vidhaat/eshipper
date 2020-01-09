package com.eshipper.repository;

import com.eshipper.domain.ClaimSolution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimSolution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimSolutionRepository extends JpaRepository<ClaimSolution, Long> {

}
