package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingClaimDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingClaim} and its DTO {@link ShippingClaimDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClaimCarrierRefundMapper.class, ClaimEshipperRefundMapper.class, ShippingOrderMapper.class, TicketReasonMapper.class, ClaimStatusMapper.class, ClaimSolutionMapper.class, ClaimAssigneeMapper.class, ContactPreferenceMapper.class})
public interface ShippingClaimMapper extends EntityMapper<ShippingClaimDTO, ShippingClaim> {

    @Mapping(source = "claimCarrierRefund.id", target = "claimCarrierRefundId")
    @Mapping(source = "claimEshipperRefund.id", target = "claimEshipperRefundId")
    @Mapping(source = "shippingOrder.id", target = "shippingOrderId")
    @Mapping(source = "ticketReason.id", target = "ticketReasonId")
    @Mapping(source = "claimStatus.id", target = "claimStatusId")
    @Mapping(source = "claimSolution.id", target = "claimSolutionId")
    @Mapping(source = "claimAssignee.id", target = "claimAssigneeId")
    @Mapping(source = "contactPreference.id", target = "contactPreferenceId")
    ShippingClaimDTO toDto(ShippingClaim shippingClaim);

    @Mapping(source = "claimCarrierRefundId", target = "claimCarrierRefund")
    @Mapping(source = "claimEshipperRefundId", target = "claimEshipperRefund")
    @Mapping(target = "claimAttachments", ignore = true)
    @Mapping(target = "removeClaimAttachment", ignore = true)
    @Mapping(target = "claimMissingDocuments", ignore = true)
    @Mapping(target = "removeClaimMissingDocument", ignore = true)
    @Mapping(target = "claimComments", ignore = true)
    @Mapping(target = "removeClaimComment", ignore = true)
    @Mapping(source = "shippingOrderId", target = "shippingOrder")
    @Mapping(source = "ticketReasonId", target = "ticketReason")
    @Mapping(source = "claimStatusId", target = "claimStatus")
    @Mapping(source = "claimSolutionId", target = "claimSolution")
    @Mapping(source = "claimAssigneeId", target = "claimAssignee")
    @Mapping(source = "contactPreferenceId", target = "contactPreference")
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
