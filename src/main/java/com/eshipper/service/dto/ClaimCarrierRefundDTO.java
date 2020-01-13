package com.eshipper.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimCarrierRefund} entity.
 */
public class ClaimCarrierRefundDTO implements Serializable {

    private Long id;

    private String carrierClaim;

    private Double amount;

    private String chequeNumber;

    private String cheque;

    private ZonedDateTime date;


    private Long currencyId;

    private Long refundStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierClaim() {
        return carrierClaim;
    }

    public void setCarrierClaim(String carrierClaim) {
        this.carrierClaim = carrierClaim;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getRefundStatusId() {
        return refundStatusId;
    }

    public void setRefundStatusId(Long claimCarrierRefundStatusId) {
        this.refundStatusId = claimCarrierRefundStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimCarrierRefundDTO claimCarrierRefundDTO = (ClaimCarrierRefundDTO) o;
        if (claimCarrierRefundDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimCarrierRefundDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimCarrierRefundDTO{" +
            "id=" + getId() +
            ", carrierClaim='" + getCarrierClaim() + "'" +
            ", amount=" + getAmount() +
            ", chequeNumber='" + getChequeNumber() + "'" +
            ", cheque='" + getCheque() + "'" +
            ", date='" + getDate() + "'" +
            ", currencyId=" + getCurrencyId() +
            ", refundStatusId=" + getRefundStatusId() +
            "}";
    }
}
