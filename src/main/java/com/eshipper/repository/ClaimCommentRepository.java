package com.eshipper.repository;

import com.eshipper.domain.ClaimComment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ClaimComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimCommentRepository extends JpaRepository<ClaimComment, Long> {

    @Query("select claimComment from ClaimComment claimComment where claimComment.user.login = ?#{principal.username}")
    List<ClaimComment> findByUserIsCurrentUser();

}
