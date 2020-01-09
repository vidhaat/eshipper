package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.CurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimCarrierRefundMapper.class, ClaimEshipperRefundMapper.class})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {

    @Mapping(source = "claimCarrierRefund.id", target = "claimCarrierRefundId")
    @Mapping(source = "claimEshipperRefund.id", target = "claimEshipperRefundId")
    CurrencyDTO toDto(Currency currency);

    @Mapping(source = "claimCarrierRefundId", target = "claimCarrierRefund")
    @Mapping(source = "claimEshipperRefundId", target = "claimEshipperRefund")
    Currency toEntity(CurrencyDTO currencyDTO);

    default Currency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(id);
        return currency;
    }
}
