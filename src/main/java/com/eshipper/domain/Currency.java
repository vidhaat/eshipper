package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties("currencies")
    private ClaimCarrierRefund claimCarrierRefund;

    @ManyToOne
    @JsonIgnoreProperties("currencies")
    private ClaimEshipperRefund claimEshipperRefund;

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

    public ClaimCarrierRefund getClaimCarrierRefund() {
        return claimCarrierRefund;
    }

    public Currency claimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefund = claimCarrierRefund;
        return this;
    }

    public void setClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefund = claimCarrierRefund;
    }

    public ClaimEshipperRefund getClaimEshipperRefund() {
        return claimEshipperRefund;
    }

    public Currency claimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefund = claimEshipperRefund;
        return this;
    }

    public void setClaimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefund = claimEshipperRefund;
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
