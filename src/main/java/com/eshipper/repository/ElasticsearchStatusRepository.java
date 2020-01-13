package com.eshipper.repository;

import com.eshipper.domain.ElasticsearchStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ElasticsearchStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticsearchStatusRepository extends JpaRepository<ElasticsearchStatus, Long> {

}
