package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "claimMissingDocument")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimDocumentType> claimDocumentTypes = new HashSet<>();

    @OneToMany(mappedBy = "claimMissingDocument")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingClaim> shippingClaims = new HashSet<>();

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

    public Set<ClaimDocumentType> getClaimDocumentTypes() {
        return claimDocumentTypes;
    }

    public ClaimMissingDocument claimDocumentTypes(Set<ClaimDocumentType> claimDocumentTypes) {
        this.claimDocumentTypes = claimDocumentTypes;
        return this;
    }

    public ClaimMissingDocument addClaimDocumentType(ClaimDocumentType claimDocumentType) {
        this.claimDocumentTypes.add(claimDocumentType);
        claimDocumentType.setClaimMissingDocument(this);
        return this;
    }

    public ClaimMissingDocument removeClaimDocumentType(ClaimDocumentType claimDocumentType) {
        this.claimDocumentTypes.remove(claimDocumentType);
        claimDocumentType.setClaimMissingDocument(null);
        return this;
    }

    public void setClaimDocumentTypes(Set<ClaimDocumentType> claimDocumentTypes) {
        this.claimDocumentTypes = claimDocumentTypes;
    }

    public Set<ShippingClaim> getShippingClaims() {
        return shippingClaims;
    }

    public ClaimMissingDocument shippingClaims(Set<ShippingClaim> shippingClaims) {
        this.shippingClaims = shippingClaims;
        return this;
    }

    public ClaimMissingDocument addShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.add(shippingClaim);
        shippingClaim.setClaimMissingDocument(this);
        return this;
    }

    public ClaimMissingDocument removeShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.remove(shippingClaim);
        shippingClaim.setClaimMissingDocument(null);
        return this;
    }

    public void setShippingClaims(Set<ShippingClaim> shippingClaims) {
        this.shippingClaims = shippingClaims;
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
