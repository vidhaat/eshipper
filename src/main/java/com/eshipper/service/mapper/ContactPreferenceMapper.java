package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ContactPreferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContactPreference} and its DTO {@link ContactPreferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ShippingClaimMapper.class})
public interface ContactPreferenceMapper extends EntityMapper<ContactPreferenceDTO, ContactPreference> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "shippingClaim.id", target = "shippingClaimId")
    ContactPreferenceDTO toDto(ContactPreference contactPreference);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "shippingClaimId", target = "shippingClaim")
    ContactPreference toEntity(ContactPreferenceDTO contactPreferenceDTO);

    default ContactPreference fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContactPreference contactPreference = new ContactPreference();
        contactPreference.setId(id);
        return contactPreference;
    }
}
