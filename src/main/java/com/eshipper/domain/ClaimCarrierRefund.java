package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ClaimCarrierRefund.
 */
@Entity
@Table(name = "claim_carrier_refund")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimCarrierRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrier_claim")
    private String carrierClaim;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "cheque_number")
    private String chequeNumber;

    @Column(name = "cheque")
    private String cheque;

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("claimCarrierRefunds")
    private Currency currency;

    @ManyToOne
    @JsonIgnoreProperties("claimCarrierRefunds")
    private ClaimCarrierRefundStatus refundStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierClaim() {
        return carrierClaim;
    }

    public ClaimCarrierRefund carrierClaim(String carrierClaim) {
        this.carrierClaim = carrierClaim;
        return this;
    }

    public void setCarrierClaim(String carrierClaim) {
        this.carrierClaim = carrierClaim;
    }

    public Double getAmount() {
        return amount;
    }

    public ClaimCarrierRefund amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public ClaimCarrierRefund chequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
        return this;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getCheque() {
        return cheque;
    }

    public ClaimCarrierRefund cheque(String cheque) {
        this.cheque = cheque;
        return this;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ClaimCarrierRefund date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public ClaimCarrierRefund currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ClaimCarrierRefundStatus getRefundStatus() {
        return refundStatus;
    }

    public ClaimCarrierRefund refundStatus(ClaimCarrierRefundStatus claimCarrierRefundStatus) {
        this.refundStatus = claimCarrierRefundStatus;
        return this;
    }

    public void setRefundStatus(ClaimCarrierRefundStatus claimCarrierRefundStatus) {
        this.refundStatus = claimCarrierRefundStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimCarrierRefund)) {
            return false;
        }
        return id != null && id.equals(((ClaimCarrierRefund) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimCarrierRefund{" +
            "id=" + getId() +
            ", carrierClaim='" + getCarrierClaim() + "'" +
            ", amount=" + getAmount() +
            ", chequeNumber='" + getChequeNumber() + "'" +
            ", cheque='" + getCheque() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
