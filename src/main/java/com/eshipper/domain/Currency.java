package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimCarrierRefund> claimCarrierRefunds = new HashSet<>();

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimEshipperRefund> claimEshipperRefunds = new HashSet<>();

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

    public Currency name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ClaimCarrierRefund> getClaimCarrierRefunds() {
        return claimCarrierRefunds;
    }

    public Currency claimCarrierRefunds(Set<ClaimCarrierRefund> claimCarrierRefunds) {
        this.claimCarrierRefunds = claimCarrierRefunds;
        return this;
    }

    public Currency addClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefunds.add(claimCarrierRefund);
        claimCarrierRefund.setCurrency(this);
        return this;
    }

    public Currency removeClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefunds.remove(claimCarrierRefund);
        claimCarrierRefund.setCurrency(null);
        return this;
    }

    public void setClaimCarrierRefunds(Set<ClaimCarrierRefund> claimCarrierRefunds) {
        this.claimCarrierRefunds = claimCarrierRefunds;
    }

    public Set<ClaimEshipperRefund> getClaimEshipperRefunds() {
        return claimEshipperRefunds;
    }

    public Currency claimEshipperRefunds(Set<ClaimEshipperRefund> claimEshipperRefunds) {
        this.claimEshipperRefunds = claimEshipperRefunds;
        return this;
    }

    public Currency addClaimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefunds.add(claimEshipperRefund);
        claimEshipperRefund.setCurrency(this);
        return this;
    }

    public Currency removeClaimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefunds.remove(claimEshipperRefund);
        claimEshipperRefund.setCurrency(null);
        return this;
    }

    public void setClaimEshipperRefunds(Set<ClaimEshipperRefund> claimEshipperRefunds) {
        this.claimEshipperRefunds = claimEshipperRefunds;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return id != null && id.equals(((Currency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
