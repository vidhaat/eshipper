package com.eshipper.service.impl;

import com.eshipper.service.ShippingClaimService;
import com.eshipper.domain.ShippingClaim;
import com.eshipper.repository.ShippingClaimRepository;
import com.eshipper.service.dto.ShippingClaimDTO;
import com.eshipper.service.mapper.ShippingClaimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShippingClaim}.
 */
@Service
@Transactional
public class ShippingClaimServiceImpl implements ShippingClaimService {

    private final Logger log = LoggerFactory.getLogger(ShippingClaimServiceImpl.class);

    private final ShippingClaimRepository shippingClaimRepository;

    private final ShippingClaimMapper shippingClaimMapper;

    public ShippingClaimServiceImpl(ShippingClaimRepository shippingClaimRepository, ShippingClaimMapper shippingClaimMapper) {
        this.shippingClaimRepository = shippingClaimRepository;
        this.shippingClaimMapper = shippingClaimMapper;
    }

    /**
     * Save a shippingClaim.
     *
     * @param shippingClaimDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShippingClaimDTO save(ShippingClaimDTO shippingClaimDTO) {
        log.debug("Request to save ShippingClaim : {}", shippingClaimDTO);
        ShippingClaim shippingClaim = shippingClaimMapper.toEntity(shippingClaimDTO);
        shippingClaim = shippingClaimRepository.save(shippingClaim);
        return shippingClaimMapper.toDto(shippingClaim);
    }

    /**
     * Get all the shippingClaims.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShippingClaimDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShippingClaims");
        return shippingClaimRepository.findAll(pageable)
            .map(shippingClaimMapper::toDto);
    }


    /**
     * Get one shippingClaim by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShippingClaimDTO> findOne(Long id) {
        log.debug("Request to get ShippingClaim : {}", id);
        return shippingClaimRepository.findById(id)
            .map(shippingClaimMapper::toDto);
    }

    /**
     * Delete the shippingClaim by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShippingClaim : {}", id);
        shippingClaimRepository.deleteById(id);
    }
}
