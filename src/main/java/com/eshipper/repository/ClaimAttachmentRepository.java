package com.eshipper.repository;

import com.eshipper.domain.ClaimAttachment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimAttachmentRepository extends JpaRepository<ClaimAttachment, Long> {

}
