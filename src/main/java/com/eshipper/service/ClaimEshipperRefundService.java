package com.eshipper.service;

import com.eshipper.service.dto.ClaimEshipperRefundDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimEshipperRefund}.
 */
public interface ClaimEshipperRefundService {

    /**
     * Save a claimEshipperRefund.
     *
     * @param claimEshipperRefundDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimEshipperRefundDTO save(ClaimEshipperRefundDTO claimEshipperRefundDTO);

    /**
     * Get all the claimEshipperRefunds.
     *
     * @return the list of entities.
     */
    List<ClaimEshipperRefundDTO> findAll();


    /**
     * Get the "id" claimEshipperRefund.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimEshipperRefundDTO> findOne(Long id);

    /**
     * Delete the "id" claimEshipperRefund.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
