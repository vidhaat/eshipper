package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.TicketReasonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TicketReason} and its DTO {@link TicketReasonDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShippingClaimMapper.class})
public interface TicketReasonMapper extends EntityMapper<TicketReasonDTO, TicketReason> {

    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    TicketReasonDTO toDto(TicketReason ticketReason);

    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    TicketReason toEntity(TicketReasonDTO ticketReasonDTO);

    default TicketReason fromId(Long id) {
        if (id == null) {
            return null;
        }
        TicketReason ticketReason = new TicketReason();
        ticketReason.setId(id);
        return ticketReason;
    }
}
