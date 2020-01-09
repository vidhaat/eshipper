package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Currency} entity.
 */
public class CurrencyDTO implements Serializable {

    private Long id;

    private String name;


    private Long claimCarrierRefundId;

    private Long claimEshipperRefundId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClaimCarrierRefundId() {
        return claimCarrierRefundId;
    }

    public void setClaimCarrierRefundId(Long claimCarrierRefundId) {
        this.claimCarrierRefundId = claimCarrierRefundId;
    }

    public Long getClaimEshipperRefundId() {
        return claimEshipperRefundId;
    }

    public void setClaimEshipperRefundId(Long claimEshipperRefundId) {
        this.claimEshipperRefundId = claimEshipperRefundId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (currencyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", claimCarrierRefundId=" + getClaimCarrierRefundId() +
            ", claimEshipperRefundId=" + getClaimEshipperRefundId() +
            "}";
    }
}
