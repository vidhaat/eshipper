package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ClaimMissingDocument.
 */
@Entity
@Table(name = "claim_missing_document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimMissingDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "notify_client")
    private Boolean notifyClient;

    @Column(name = "uploaded")
    private Boolean uploaded;

    @ManyToOne
    @JsonIgnoreProperties("claimMissingDocuments")
    private ClaimDocumentType claimDocumentType;

    @ManyToOne
    @JsonIgnoreProperties("claimMissingDocuments")
    private ShippingClaim shippingClaim;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public ClaimMissingDocument documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean isNotifyClient() {
        return notifyClient;
    }

    public ClaimMissingDocument notifyClient(Boolean notifyClient) {
        this.notifyClient = notifyClient;
        return this;
    }

    public void setNotifyClient(Boolean notifyClient) {
        this.notifyClient = notifyClient;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public ClaimMissingDocument uploaded(Boolean uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public ClaimDocumentType getClaimDocumentType() {
        return claimDocumentType;
    }

    public ClaimMissingDocument claimDocumentType(ClaimDocumentType claimDocumentType) {
        this.claimDocumentType = claimDocumentType;
        return this;
    }

    public void setClaimDocumentType(ClaimDocumentType claimDocumentType) {
        this.claimDocumentType = claimDocumentType;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ClaimMissingDocument shippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
        return this;
    }

    public void setShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimMissingDocument)) {
            return false;
        }
        return id != null && id.equals(((ClaimMissingDocument) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimMissingDocument{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", notifyClient='" + isNotifyClient() + "'" +
            ", uploaded='" + isUploaded() + "'" +
            "}";
    }
}
