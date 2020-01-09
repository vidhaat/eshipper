package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingOrder} and its DTO {@link ShippingOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class})
public interface ShippingOrderMapper extends EntityMapper<ShippingOrderDTO, ShippingOrder> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ShippingOrderDTO toDto(ShippingOrder shippingOrder);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    ShippingOrder toEntity(ShippingOrderDTO shippingOrderDTO);

    default ShippingOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setId(id);
        return shippingOrder;
    }
}
