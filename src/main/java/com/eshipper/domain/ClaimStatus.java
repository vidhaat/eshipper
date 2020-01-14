package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaimStatus.
 */
@Entity
@Table(name = "claim_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "claimStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingClaim> shippingClaims = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ClaimStatus name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ShippingClaim> getShippingClaims() {
        return shippingClaims;
    }

    public ClaimStatus shippingClaims(Set<ShippingClaim> shippingClaims) {
        this.shippingClaims = shippingClaims;
        return this;
    }

    public ClaimStatus addShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.add(shippingClaim);
        shippingClaim.setClaimStatus(this);
        return this;
    }

    public ClaimStatus removeShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.remove(shippingClaim);
        shippingClaim.setClaimStatus(null);
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
        if (!(o instanceof ClaimStatus)) {
            return false;
        }
        return id != null && id.equals(((ClaimStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimStatus{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
