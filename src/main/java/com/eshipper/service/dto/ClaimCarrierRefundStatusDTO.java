package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimCarrierRefundStatus} entity.
 */
public class ClaimCarrierRefundStatusDTO implements Serializable {

    private Long id;

    private String status;


    private Long claimCarrierRefundId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClaimCarrierRefundId() {
        return claimCarrierRefundId;
    }

    public void setClaimCarrierRefundId(Long claimCarrierRefundId) {
        this.claimCarrierRefundId = claimCarrierRefundId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimCarrierRefundStatusDTO claimCarrierRefundStatusDTO = (ClaimCarrierRefundStatusDTO) o;
        if (claimCarrierRefundStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimCarrierRefundStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimCarrierRefundStatusDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", claimCarrierRefundId=" + getClaimCarrierRefundId() +
            "}";
    }
}
