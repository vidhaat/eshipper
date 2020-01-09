package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ClaimSolution.
 */
@Entity
@Table(name = "claim_solution")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimSolution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("claimSolutions")
    private ShippingClaim shippingClaim;

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

    public ClaimSolution name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ClaimSolution shippingClaim(ShippingClaim shippingClaim) {
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
        if (!(o instanceof ClaimSolution)) {
            return false;
        }
        return id != null && id.equals(((ClaimSolution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimSolution{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
