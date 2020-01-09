package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties("shippingOrders")
    private ShippingClaim shippingClaim;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ShippingOrder shippingClaim(ShippingClaim shippingClaim) {
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
