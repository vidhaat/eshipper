package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ElasticsearchStatus} entity.
 */
public class ElasticsearchStatusDTO implements Serializable {

    private Long id;


    private Long elasticShippingClaimId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElasticShippingClaimId() {
        return elasticShippingClaimId;
    }

    public void setElasticShippingClaimId(Long elasticShippingClaimId) {
        this.elasticShippingClaimId = elasticShippingClaimId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ElasticsearchStatusDTO elasticsearchStatusDTO = (ElasticsearchStatusDTO) o;
        if (elasticsearchStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), elasticsearchStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElasticsearchStatusDTO{" +
            "id=" + getId() +
            ", elasticShippingClaimId=" + getElasticShippingClaimId() +
            "}";
    }
}
