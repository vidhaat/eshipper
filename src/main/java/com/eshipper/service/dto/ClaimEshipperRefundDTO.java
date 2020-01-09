package com.eshipper.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimEshipperRefund} entity.
 */
public class ClaimEshipperRefundDTO implements Serializable {

    private Long id;

    private Double amount;

    private String cheque;

    private ZonedDateTime date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimEshipperRefundDTO claimEshipperRefundDTO = (ClaimEshipperRefundDTO) o;
        if (claimEshipperRefundDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimEshipperRefundDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimEshipperRefundDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", cheque='" + getCheque() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
