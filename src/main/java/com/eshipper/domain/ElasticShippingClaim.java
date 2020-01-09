package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ElasticShippingClaim.
 */
@Entity
@Table(name = "elastic_shipping_claim")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElasticShippingClaim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private ShippingClaim shippingClaim;

    @OneToMany(mappedBy = "elasticShippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ElasticsearchStatus> elasticStatuses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ElasticShippingClaim shippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
        return this;
    }

    public void setShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
    }

    public Set<ElasticsearchStatus> getElasticStatuses() {
        return elasticStatuses;
    }

    public ElasticShippingClaim elasticStatuses(Set<ElasticsearchStatus> elasticsearchStatuses) {
        this.elasticStatuses = elasticsearchStatuses;
        return this;
    }

    public ElasticShippingClaim addElasticStatus(ElasticsearchStatus elasticsearchStatus) {
        this.elasticStatuses.add(elasticsearchStatus);
        elasticsearchStatus.setElasticShippingClaim(this);
        return this;
    }

    public ElasticShippingClaim removeElasticStatus(ElasticsearchStatus elasticsearchStatus) {
        this.elasticStatuses.remove(elasticsearchStatus);
        elasticsearchStatus.setElasticShippingClaim(null);
        return this;
    }

    public void setElasticStatuses(Set<ElasticsearchStatus> elasticsearchStatuses) {
        this.elasticStatuses = elasticsearchStatuses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticShippingClaim)) {
            return false;
        }
        return id != null && id.equals(((ElasticShippingClaim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ElasticShippingClaim{" +
            "id=" + getId() +
            "}";
    }
}
