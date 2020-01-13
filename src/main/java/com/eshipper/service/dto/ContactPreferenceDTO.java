package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ContactPreference} entity.
 */
public class ContactPreferenceDTO implements Serializable {

    private Long id;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactPreferenceDTO contactPreferenceDTO = (ContactPreferenceDTO) o;
        if (contactPreferenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactPreferenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactPreferenceDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            "}";
    }
}
