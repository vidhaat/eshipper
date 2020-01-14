package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ShippingOrder.
 */
@Entity
@Table(name = "shipping_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShippingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shippingOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingClaim> shippingClaims = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ShippingClaim> getShippingClaims() {
        return shippingClaims;
    }

    public ShippingOrder shippingClaims(Set<ShippingClaim> shippingClaims) {
        this.shippingClaims = shippingClaims;
        return this;
    }

    public ShippingOrder addShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.add(shippingClaim);
        shippingClaim.setShippingOrder(this);
        return this;
    }

    public ShippingOrder removeShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaims.remove(shippingClaim);
        shippingClaim.setShippingOrder(null);
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
        if (!(o instanceof ShippingOrder)) {
            return false;
        }
        return id != null && id.equals(((ShippingOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShippingOrder{" +
            "id=" + getId() +
            "}";
    }
}
