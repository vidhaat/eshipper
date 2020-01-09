package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimSolution} entity.
 */
public class ClaimSolutionDTO implements Serializable {

    private Long id;

    private String name;


    private Long shippingClaimId;

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

    public Long getShippingClaimId() {
        return shippingClaimId;
    }

    public void setShippingClaimId(Long shippingClaimId) {
        this.shippingClaimId = shippingClaimId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimSolutionDTO claimSolutionDTO = (ClaimSolutionDTO) o;
        if (claimSolutionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimSolutionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimSolutionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shippingClaimId=" + getShippingClaimId() +
            "}";
    }
}
