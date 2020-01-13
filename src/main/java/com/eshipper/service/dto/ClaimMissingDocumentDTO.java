package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimMissingDocument} entity.
 */
public class ClaimMissingDocumentDTO implements Serializable {

    private Long id;

    private String documentName;

    private Boolean notifyClient;

    private Boolean uploaded;


    private Long claimDocumentTypeId;

    private Long shippingClaimId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean isNotifyClient() {
        return notifyClient;
    }

    public void setNotifyClient(Boolean notifyClient) {
        this.notifyClient = notifyClient;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Long getClaimDocumentTypeId() {
        return claimDocumentTypeId;
    }

    public void setClaimDocumentTypeId(Long claimDocumentTypeId) {
        this.claimDocumentTypeId = claimDocumentTypeId;
    }

    public Long getShippingClaimId() {
        return shippingClaimId;
    }

    public void setShippingClaimId(Long shippingClaimId) {
        this.shippingClaimId = shippingClaimId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimMissingDocumentDTO claimMissingDocumentDTO = (ClaimMissingDocumentDTO) o;
        if (claimMissingDocumentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimMissingDocumentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimMissingDocumentDTO{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", notifyClient='" + isNotifyClient() + "'" +
            ", uploaded='" + isUploaded() + "'" +
            ", claimDocumentTypeId=" + getClaimDocumentTypeId() +
            ", shippingClaimId=" + getShippingClaimId() +
            "}";
    }
}
