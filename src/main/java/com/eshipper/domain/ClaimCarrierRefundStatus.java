package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaimCarrierRefundStatus.
 */
@Entity
@Table(name = "claim_carrier_refund_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimCarrierRefundStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "refundStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimCarrierRefund> claimCarrierRefunds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public ClaimCarrierRefundStatus status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ClaimCarrierRefund> getClaimCarrierRefunds() {
        return claimCarrierRefunds;
    }

    public ClaimCarrierRefundStatus claimCarrierRefunds(Set<ClaimCarrierRefund> claimCarrierRefunds) {
        this.claimCarrierRefunds = claimCarrierRefunds;
        return this;
    }

    public ClaimCarrierRefundStatus addClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefunds.add(claimCarrierRefund);
        claimCarrierRefund.setRefundStatus(this);
        return this;
    }

    public ClaimCarrierRefundStatus removeClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefunds.remove(claimCarrierRefund);
        claimCarrierRefund.setRefundStatus(null);
        return this;
    }

    public void setClaimCarrierRefunds(Set<ClaimCarrierRefund> claimCarrierRefunds) {
        this.claimCarrierRefunds = claimCarrierRefunds;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimCarrierRefundStatus)) {
            return false;
        }
        return id != null && id.equals(((ClaimCarrierRefundStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimCarrierRefundStatus{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
