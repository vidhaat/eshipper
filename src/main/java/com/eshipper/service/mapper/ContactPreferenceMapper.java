package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ContactPreferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContactPreference} and its DTO {@link ContactPreferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ContactPreferenceMapper extends EntityMapper<ContactPreferenceDTO, ContactPreference> {

    @Mapping(source = "user.id", target = "userId")
    ContactPreferenceDTO toDto(ContactPreference contactPreference);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "shippingClaims", ignore = true)
    @Mapping(target = "removeShippingClaim", ignore = true)
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
