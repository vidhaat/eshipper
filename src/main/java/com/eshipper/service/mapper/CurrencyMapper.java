package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.CurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {


    @Mapping(target = "claimCarrierRefunds", ignore = true)
    @Mapping(target = "removeClaimCarrierRefund", ignore = true)
    @Mapping(target = "claimEshipperRefunds", ignore = true)
    @Mapping(target = "removeClaimEshipperRefund", ignore = true)
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
