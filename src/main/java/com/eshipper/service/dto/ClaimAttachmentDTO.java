package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimAttachment} entity.
 */
public class ClaimAttachmentDTO implements Serializable {

    private Long id;

    private String attachmentPath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimAttachmentDTO claimAttachmentDTO = (ClaimAttachmentDTO) o;
        if (claimAttachmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimAttachmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimAttachmentDTO{" +
            "id=" + getId() +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            "}";
    }
}
