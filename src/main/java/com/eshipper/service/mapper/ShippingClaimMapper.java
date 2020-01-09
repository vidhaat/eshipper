package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingClaimDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingClaim} and its DTO {@link ShippingClaimDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimCarrierRefundMapper.class, ClaimEshipperRefundMapper.class, ClaimAttachmentMapper.class, ClaimMissingDocumentMapper.class})
public interface ShippingClaimMapper extends EntityMapper<ShippingClaimDTO, ShippingClaim> {

    @Mapping(source = "claimCarrierRefund.id", target = "claimCarrierRefundId")
    @Mapping(source = "claimEshipperRefund.id", target = "claimEshipperRefundId")
    @Mapping(source = "claimAttachment.id", target = "claimAttachmentId")
    @Mapping(source = "claimMissingDocument.id", target = "claimMissingDocumentId")
    ShippingClaimDTO toDto(ShippingClaim shippingClaim);

    @Mapping(source = "claimCarrierRefundId", target = "claimCarrierRefund")
    @Mapping(source = "claimEshipperRefundId", target = "claimEshipperRefund")
    @Mapping(target = "shippingOrders", ignore = true)
    @Mapping(target = "removeShippingOrder", ignore = true)
    @Mapping(target = "ticketReasons", ignore = true)
    @Mapping(target = "removeTicketReason", ignore = true)
    @Mapping(target = "claimStatuses", ignore = true)
    @Mapping(target = "removeClaimStatus", ignore = true)
    @Mapping(target = "claimSolutions", ignore = true)
    @Mapping(target = "removeClaimSolution", ignore = true)
    @Mapping(target = "claimAssignees", ignore = true)
    @Mapping(target = "removeClaimAssignee", ignore = true)
    @Mapping(target = "claimComments", ignore = true)
    @Mapping(target = "removeClaimComment", ignore = true)
    @Mapping(target = "contactPreferences", ignore = true)
    @Mapping(target = "removeContactPreference", ignore = true)
    @Mapping(source = "claimAttachmentId", target = "claimAttachment")
    @Mapping(source = "claimMissingDocumentId", target = "claimMissingDocument")
    ShippingClaim toEntity(ShippingClaimDTO shippingClaimDTO);

    default ShippingClaim fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShippingClaim shippingClaim = new ShippingClaim();
        shippingClaim.setId(id);
        return shippingClaim;
    }
}
