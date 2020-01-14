package com.eshipper.repository;

import com.eshipper.domain.ClaimComment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimCommentRepository extends JpaRepository<ClaimComment, Long> {

}
