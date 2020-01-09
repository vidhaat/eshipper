package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElasticsearchStatus.
 */
@Entity
@Table(name = "elasticsearch_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElasticsearchStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("elasticStatuses")
    private ElasticShippingClaim elasticShippingClaim;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElasticShippingClaim getElasticShippingClaim() {
        return elasticShippingClaim;
    }

    public ElasticsearchStatus elasticShippingClaim(ElasticShippingClaim elasticShippingClaim) {
        this.elasticShippingClaim = elasticShippingClaim;
        return this;
    }

    public void setElasticShippingClaim(ElasticShippingClaim elasticShippingClaim) {
        this.elasticShippingClaim = elasticShippingClaim;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticsearchStatus)) {
            return false;
        }
        return id != null && id.equals(((ElasticsearchStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ElasticsearchStatus{" +
            "id=" + getId() +
            "}";
    }
}
