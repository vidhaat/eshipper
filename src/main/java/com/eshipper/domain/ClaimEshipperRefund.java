package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaimEshipperRefund.
 */
@Entity
@Table(name = "claim_eshipper_refund")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimEshipperRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "cheque")
    private String cheque;

    @Column(name = "date")
    private ZonedDateTime date;

    @OneToMany(mappedBy = "claimEshipperRefund")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Currency> currencies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public ClaimEshipperRefund amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCheque() {
        return cheque;
    }

    public ClaimEshipperRefund cheque(String cheque) {
        this.cheque = cheque;
        return this;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ClaimEshipperRefund date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public ClaimEshipperRefund currencies(Set<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public ClaimEshipperRefund addCurrency(Currency currency) {
        this.currencies.add(currency);
        currency.setClaimEshipperRefund(this);
        return this;
    }

    public ClaimEshipperRefund removeCurrency(Currency currency) {
        this.currencies.remove(currency);
        currency.setClaimEshipperRefund(null);
        return this;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimEshipperRefund)) {
            return false;
        }
        return id != null && id.equals(((ClaimEshipperRefund) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimEshipperRefund{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", cheque='" + getCheque() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
