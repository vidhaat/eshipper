package com.eshipper.service;

import com.eshipper.service.dto.ClaimCarrierRefundDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ClaimCarrierRefund}.
 */
public interface ClaimCarrierRefundService {

    /**
     * Save a claimCarrierRefund.
     *
     * @param claimCarrierRefundDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimCarrierRefundDTO save(ClaimCarrierRefundDTO claimCarrierRefundDTO);

    /**
     * Get all the claimCarrierRefunds.
     *
     * @return the list of entities.
     */
    List<ClaimCarrierRefundDTO> findAll();


    /**
     * Get the "id" claimCarrierRefund.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimCarrierRefundDTO> findOne(Long id);

    /**
     * Delete the "id" claimCarrierRefund.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
