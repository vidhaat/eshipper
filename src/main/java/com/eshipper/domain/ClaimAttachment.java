package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaimAttachment.
 */
@Entity
@Table(name = "claim_attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @OneToMany(mappedBy = "claimAttachment")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingClaim> shippingClaims = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public ClaimAttachment attachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        return this;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Set<ShippingClaim> getShippingClaims() {
        return shippingClaims;
    }

    public ClaimAttachment shippingClaims(Set<ShippingClaim> shippingClaims) {
        this.shippingClaims = shippingClaims;
        return this;
    }

    public ClaimAttachment addShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.add(shippingClaim);
        shippingClaim.setClaimAttachment(this);
        return this;
    }

    public ClaimAttachment removeShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.remove(shippingClaim);
        shippingClaim.setClaimAttachment(null);
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
        if (!(o instanceof ClaimAttachment)) {
            return false;
        }
        return id != null && id.equals(((ClaimAttachment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimAttachment{" +
            "id=" + getId() +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            "}";
    }
}
